package br.com.ead.auth.controllers;

import br.com.ead.auth.clients.CursoClient;
import br.com.ead.auth.dtos.CursoDTO;
import br.com.ead.auth.dtos.MatriculaUsuarioDTO;
import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.models.UsuarioCursoModel;
import br.com.ead.auth.services.UserService;
import br.com.ead.auth.services.UsuarioCursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioCursoController {

    @Autowired
    CursoClient cursoClient;

    @Autowired
    UserService userService;

    @Autowired
    UsuarioCursoService usuarioCursoService;

    @GetMapping("/usuarios/{idUsuario}/cursos")
    public ResponseEntity<Page<CursoDTO>> getAllCursosByUsuario(@PathVariable(value = "idUsuario") Long idUsuario,
                                                                @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cursoClient.getAllCursosByUsuario(idUsuario, pageable));
    }

    @PostMapping("/usuarios/{idUsuario}/cursos/matricula")
    public ResponseEntity<Object> matricularUsuarioInCurso(@PathVariable(value = "idUsuario") Long idUsuario,
                                                           @RequestBody @Valid MatriculaUsuarioDTO matriculaUsuarioDTO) {
        Optional<UserModel> userModelOptional = this.userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        if (this.usuarioCursoService.existsByUsuarioAndCurso(userModelOptional.get(), matriculaUsuarioDTO.getIdCurso())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Aluno já matriculado para esse curso!");
        }

        UsuarioCursoModel usuarioCursoModel = this.usuarioCursoService.save(userModelOptional.get().convertToUsuarioCursoModel(matriculaUsuarioDTO.getIdCurso()));

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCursoModel);
    }

    @DeleteMapping("/usuarios/cursos/{idCurso}")
    public ResponseEntity<Object> deleteUserCourseByCourse(@PathVariable(value = "idCurso") Long idCurso) {
        if (!this.usuarioCursoService.existsByIdCurso(idCurso)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UsuarioCurso não encontrado.");
        }
        this.usuarioCursoService.deleteUsuarioCursoByCurso(idCurso);

        return ResponseEntity.status(HttpStatus.OK).body("UsuarioCurso apagado com sucesso.");
    }
}
