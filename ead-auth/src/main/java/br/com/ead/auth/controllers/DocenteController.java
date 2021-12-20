package br.com.ead.auth.controllers;

import br.com.ead.auth.dtos.DocenteDTO;
import br.com.ead.auth.enums.TipoRole;
import br.com.ead.auth.enums.UsuarioTipo;
import br.com.ead.auth.models.RoleModel;
import br.com.ead.auth.models.UsuarioModel;
import br.com.ead.auth.services.RoleService;
import br.com.ead.auth.services.UsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/docentes")
public class DocenteController {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    UsuarioService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("/registro")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> saveRegistroDocente(@RequestBody @Valid DocenteDTO docenteDTO) {
        Optional<UsuarioModel> userModelOptional = this.userService.findById(docenteDTO.getIdUsuario());

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        } else {
            RoleModel roleModel = this.roleService.findByRoleNome(TipoRole.ROLE_DOCENTE)
                    .orElseThrow(() -> new RuntimeException("Erro: Role não encontrada"));

            UsuarioModel usuarioModel = userModelOptional.get();
            usuarioModel.setTipo(UsuarioTipo.DOCENTE);
            usuarioModel.setDataUltimaAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));
            usuarioModel.getRoles().add(roleModel);

            userService.updateUsuarioAndPublishRabbitMQ(usuarioModel);

            return ResponseEntity.status(HttpStatus.OK).body(usuarioModel);
        }
    }

}
