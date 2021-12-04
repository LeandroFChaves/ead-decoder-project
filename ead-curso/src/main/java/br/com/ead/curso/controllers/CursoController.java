package br.com.ead.curso.controllers;

import br.com.ead.curso.dtos.CursoDTO;
import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.services.CursoService;
import br.com.ead.curso.specifications.SpecificationTemplate;
import br.com.ead.curso.validations.CursoValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CursoController {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    CursoService cursoService;

    @Autowired
    CursoValidator cursoValidator;

    @GetMapping
    public ResponseEntity<Page<CursoModel>> getAllCursos(SpecificationTemplate.CursoSpec spec,
                                                         @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                         @RequestParam(required = false) Long idUsuario) {
        if (idUsuario != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.cursoService.findAll(SpecificationTemplate.cursoUsuarioId(idUsuario).and(spec), pageable));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(this.cursoService.findAll(spec, pageable));
        }
    }

    @GetMapping("/{idCurso}")
    public ResponseEntity<Object> getCurso(@PathVariable(value = "idCurso") Long idCurso) {
        Optional<CursoModel> cursoModelOptional = this.cursoService.findById(idCurso);

        if (cursoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(cursoModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveCurso(@RequestBody CursoDTO cursoDTO, Errors errors) {
        log.debug("POST saveCurso cursoDTO recebido {} ", cursoDTO.toString());
        this.cursoValidator.validate(cursoDTO, errors);

        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors());
        }

        CursoModel cursoModel = new CursoModel();
        BeanUtils.copyProperties(cursoDTO, cursoModel);

        cursoModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        cursoModel.setDataUltimaAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(this.cursoService.save(cursoModel));
    }

    @PutMapping("/{idCurso}")
    public ResponseEntity<Object> updateCurso(@PathVariable(value = "idCurso") Long idCurso,
                                              @RequestBody @Valid CursoDTO cursoDTO) {
        Optional<CursoModel> cursoModelOptional = this.cursoService.findById(idCurso);

        if (cursoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado.");
        }

        CursoModel cursoModel = cursoModelOptional.get();

        cursoModel.setNome(cursoDTO.getNome());
        cursoModel.setDescricao(cursoDTO.getDescricao());
        cursoModel.setImagemUrl(cursoDTO.getImagemUrl());
        cursoModel.setSituacao(cursoDTO.getSituacao());
        cursoModel.setNivel(cursoDTO.getNivel());
        cursoModel.setDataUltimaAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.OK).body(this.cursoService.save(cursoModel));
    }

    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Object> deleteCurso(@PathVariable(value = "idCurso") Long idCurso) {
        Optional<CursoModel> cursoModelOptional = this.cursoService.findById(idCurso);

        if (cursoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado.");
        }

        this.cursoService.delete(cursoModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Curso apagado com sucesso.");
    }
}
