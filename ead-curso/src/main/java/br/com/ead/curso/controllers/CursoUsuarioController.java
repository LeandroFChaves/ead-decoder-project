package br.com.ead.curso.controllers;

import br.com.ead.curso.clients.UsuarioClient;
import br.com.ead.curso.dtos.MatriculaCursoDTO;
import br.com.ead.curso.dtos.UsuarioDTO;
import br.com.ead.curso.enums.UsuarioSituacao;
import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.CursoUsuarioModel;
import br.com.ead.curso.services.CursoService;
import br.com.ead.curso.services.CursoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CursoUsuarioController {

    @Autowired
    UsuarioClient usuarioClient;

    @Autowired
    CursoService cursoService;

    @Autowired
    CursoUsuarioService cursoUsuarioService;

    @GetMapping("/cursos/{idCurso}/usuarios")
    public ResponseEntity<Object> getAllUsuariosByCurso(@PathVariable(value = "idCurso") Long idCurso,
                                                        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Optional<CursoModel> cursoModelOptional = this.cursoService.findById(idCurso);
        if (!cursoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioClient.getAllUsuariosByCurso(idCurso, pageable));
    }

    @PostMapping("/cursos/{idCurso}/usuarios/matricula")
    public ResponseEntity<Object> matricularUsuarioInCurso(@PathVariable(value = "idCurso") Long idCurso,
                                                           @RequestBody @Valid MatriculaCursoDTO matriculaCursoDTO) {
        ResponseEntity<UsuarioDTO> responseUsuario;
        Optional<CursoModel> cursoModelOptional = this.cursoService.findById(idCurso);

        if (!cursoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado.");
        }

        if (this.cursoUsuarioService.existsByCursoAndUsuario(cursoModelOptional.get(), matriculaCursoDTO.getIdUsuario())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Aluno já matriculado para esse curso!");
        }

        try {
            responseUsuario = this.usuarioClient.getOneUsuarioById(matriculaCursoDTO.getIdUsuario());

            if (responseUsuario.getBody().getSituacao().equals(UsuarioSituacao.BLOQUEADO)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário bloqueado.");
            }
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
            }
        }

        CursoUsuarioModel cursoUsuarioModel = cursoModelOptional.get().convertToCursoUsuarioModel(matriculaCursoDTO.getIdUsuario());

        CursoUsuarioModel cursoUsuarioResult = this.cursoUsuarioService.saveAndMatriculaUsuarioInCurso(cursoUsuarioModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(cursoUsuarioResult);
    }

    @DeleteMapping("/cursos/usuarios/{idUsuario}")
    public ResponseEntity<Object> deleteCourseUserByUser(@PathVariable(value = "idUsuario") Long idUsuario) {
        if (!this.cursoUsuarioService.existsByIdUsuario(idUsuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CursoUsuario não encontrado.");
        }

        this.cursoUsuarioService.deleteCursoUsuarioByUsuario(idUsuario);

        return ResponseEntity.status(HttpStatus.OK).body("CursoUsuario apagado com sucesso.");
    }
}
