package br.com.ead.auth.controllers;

import br.com.ead.auth.dtos.UserDTO;
import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.services.UserService;
import br.com.ead.auth.specifications.SpecificationTemplate;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUsuarios(
            SpecificationTemplate.UserSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<UserModel> userModelPage = this.userService.findAll(spec, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Optional<UserModel> userModelOptional = this.userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "idUsuario") Long idUsuario,
                                                @RequestBody @Validated(UserDTO.UserView.CadastroPut.class)
                                                @JsonView(UserDTO.UserView.CadastroPut.class) UserDTO userDto) {
        Optional<UserModel> userModelOptional = this.userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        } else {
            UserModel userModel = userModelOptional.get();

            userModel.setNomeCompleto(userDto.getNomeCompleto());
            userModel.setTelefone(userDto.getTelefone());
            userModel.setCpf(userDto.getCpf());
            userModel.setDataUltimaAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

            this.userService.save(userModel);

            return ResponseEntity.status(HttpStatus.OK).body(userModel);
        }
    }

    @PutMapping("/{idUsuario}/senha")
    public ResponseEntity<Object> updateSenha(@PathVariable(value = "idUsuario") Long idUsuario,
                                              @RequestBody @Validated(UserDTO.UserView.SenhaPut.class)
                                              @JsonView(UserDTO.UserView.SenhaPut.class) UserDTO userDto) {
        Optional<UserModel> userModelOptional = this.userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        if (!userModelOptional.get().getSenha().equals(userDto.getSenhaAnterior())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Senha anterior incorreta!");
        } else {
            UserModel userModel = userModelOptional.get();

            userModel.setSenha(userDto.getSenha());
            userModel.setDataUltimaAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

            userService.save(userModel);

            return ResponseEntity.status(HttpStatus.OK).body("Senha atualizada com sucesso.");
        }
    }

    @PutMapping("/{idUsuario}/imagem")
    public ResponseEntity<Object> updateImagem(@PathVariable(value = "idUsuario") Long idUsuario,
                                               @RequestBody @Validated(UserDTO.UserView.ImagemPut.class)
                                               @JsonView(UserDTO.UserView.ImagemPut.class) UserDTO userDto) {
        Optional<UserModel> userModelOptional = userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        } else {
            UserModel userModel = userModelOptional.get();

            userModel.setImagemUrl(userDto.getImagemUrl());
            userModel.setDataUltimaAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

            this.userService.save(userModel);

            return ResponseEntity.status(HttpStatus.OK).body(userModel);
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Optional<UserModel> userModelOptional = this.userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        } else {
            this.userService.delete(userModelOptional.get());

            return ResponseEntity.status(HttpStatus.OK).body("Usuário apagado com sucesso");
        }
    }
}
