package br.com.ead.curso.controllers;

import br.com.ead.curso.dtos.MatriculaCursoDTO;
import br.com.ead.curso.enums.UsuarioSituacao;
import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.UsuarioModel;
import br.com.ead.curso.services.CursoService;
import br.com.ead.curso.services.UsuarioService;
import br.com.ead.curso.specifications.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CursoUsuarioController {

    @Autowired
    CursoService cursoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/cursos/{idCurso}/usuarios")
    public ResponseEntity<Object> getAllUsuariosByCurso(SpecificationTemplate.UsuarioSpec spec,
                                                        @PathVariable(value = "idCurso") Long idCurso,
                                                        @PageableDefault(page = 0, size = 10, sort = "idUsuario", direction = Sort.Direction.ASC) Pageable pageable) {
        Optional<CursoModel> cursoModelOptional = this.cursoService.findById(idCurso);
        if (cursoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.findAll(SpecificationTemplate.usuarioCursoId(idCurso).and(spec), pageable));
    }

    @PostMapping("/cursos/{idCurso}/usuarios/matricula")
    public ResponseEntity<Object> matricularUsuarioInCurso(@PathVariable(value = "idCurso") Long idCurso,
                                                           @RequestBody @Valid MatriculaCursoDTO matriculaCursoDTO) {
        Optional<CursoModel> cursoModelOptional = this.cursoService.findById(idCurso);
        Optional<UsuarioModel> userModelOptional = this.usuarioService.findById(matriculaCursoDTO.getIdUsuario());

        if (cursoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado.");
        }

        if (this.cursoService.existsByCursoAndUsuario(idCurso, matriculaCursoDTO.getIdUsuario())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: O aluno informado já se encontra matriculado para esse curso.");
        }

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        if (userModelOptional.get().getSituacao().equals(UsuarioSituacao.BLOQUEADO.toString())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário está bloqueado.");
        }

        this.cursoService.saveMatriculaUsuarioInCursoAndEnviaNotificacao(cursoModelOptional.get(), userModelOptional.get());

        return ResponseEntity.status(HttpStatus.CREATED).body("Matrícula realizada com sucesso!");
    }

}
