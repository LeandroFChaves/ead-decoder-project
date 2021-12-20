package br.com.ead.auth.controllers;

import br.com.ead.auth.configs.security.AuthenticationCurrentUserService;
import br.com.ead.auth.configs.security.UserDetailsImpl;
import br.com.ead.auth.dtos.UsuarioDTO;
import br.com.ead.auth.models.UsuarioModel;
import br.com.ead.auth.services.UsuarioService;
import br.com.ead.auth.specifications.SpecificationTemplate;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuarios")
public class UsuarioController {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    UsuarioService userService;

    @Autowired
    AuthenticationCurrentUserService authenticationCurrentUserService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ESTUDANTE')")
    public ResponseEntity<Page<UsuarioModel>> getAllUsuarios(
            SpecificationTemplate.UserSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "idUsuario", direction = Sort.Direction.ASC) Pageable pageable,
            Authentication authentication) {
        UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();
        log.debug("Usuário autenticado: {}", userDetails.getUsername());

        Page<UsuarioModel> userModelPage = this.userService.findAll(spec, pageable);

        if (!userModelPage.isEmpty()) {
            for (UsuarioModel user : userModelPage.toList()) {
                user.add(linkTo(methodOn(UsuarioController.class).getUsuario(user.getIdUsuario())).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
    }

    @GetMapping("/{idUsuario}")
    @PreAuthorize("hasAnyRole('ESTUDANTE')")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Long currentIdUsuario = authenticationCurrentUserService.getCurrentUser().getIdUsuario();

        if (currentIdUsuario.equals(idUsuario)) {
            Optional<UsuarioModel> userModelOptional = this.userService.findById(idUsuario);

            if (userModelOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }

            return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
        } else {
            throw new AccessDeniedException("Acesso negado.");
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "idUsuario") Long idUsuario,
                                                @RequestBody @Validated(UsuarioDTO.UserView.CadastroPut.class)
                                                @JsonView(UsuarioDTO.UserView.CadastroPut.class) UsuarioDTO userDto) {
        Optional<UsuarioModel> userModelOptional = this.userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        } else {
            UsuarioModel usuarioModel = userModelOptional.get();

            if (userDto.getNomeCompleto() != null) {
                usuarioModel.setNomeCompleto(userDto.getNomeCompleto());
            }
            if (userDto.getTelefone() != null) {
                usuarioModel.setTelefone(userDto.getTelefone());
            }
            if (userDto.getCpf() != null) {
                usuarioModel.setCpf(userDto.getCpf());
            }

            usuarioModel.setDataUltimaAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

            this.userService.updateUsuarioAndPublishRabbitMQ(usuarioModel);

            return ResponseEntity.status(HttpStatus.OK).body(usuarioModel);
        }
    }

    @PutMapping("/{idUsuario}/senha")
    public ResponseEntity<Object> updateSenha(@PathVariable(value = "idUsuario") Long idUsuario,
                                              @RequestBody @Validated(UsuarioDTO.UserView.SenhaPut.class)
                                              @JsonView(UsuarioDTO.UserView.SenhaPut.class) UsuarioDTO userDto) {
        Optional<UsuarioModel> userModelOptional = this.userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        if (!userModelOptional.get().getSenha().equals(userDto.getSenhaAnterior())) {
            log.warn("Senha anterior incorreta userId {}", userDto.getIdExterno());

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Senha anterior incorreta!");
        } else {
            UsuarioModel usuarioModel = userModelOptional.get();

            usuarioModel.setSenha(userDto.getSenha());
            usuarioModel.setDataUltimaAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

            userService.save(usuarioModel);

            return ResponseEntity.status(HttpStatus.OK).body("Senha atualizada com sucesso.");
        }
    }

    @PutMapping("/{idUsuario}/imagem")
    public ResponseEntity<Object> updateImagem(@PathVariable(value = "idUsuario") Long idUsuario,
                                               @RequestBody @Validated(UsuarioDTO.UserView.ImagemPut.class)
                                               @JsonView(UsuarioDTO.UserView.ImagemPut.class) UsuarioDTO userDto) {
        Optional<UsuarioModel> userModelOptional = userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        } else {
            UsuarioModel usuarioModel = userModelOptional.get();

            usuarioModel.setImagemUrl(userDto.getImagemUrl());
            usuarioModel.setDataUltimaAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

            this.userService.updateUsuarioAndPublishRabbitMQ(usuarioModel);

            return ResponseEntity.status(HttpStatus.OK).body(usuarioModel);
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Optional<UsuarioModel> userModelOptional = this.userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        } else {
            this.userService.deleteUsuarioAndPublishRabbitMQ(userModelOptional.get());

            return ResponseEntity.status(HttpStatus.OK).body("Usuário apagado com sucesso");
        }
    }
}
