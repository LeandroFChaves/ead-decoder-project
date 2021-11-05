package br.com.ead.curso.controllers;

import br.com.ead.curso.dtos.ModuloDTO;
import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.ModuloModel;
import br.com.ead.curso.services.CursoService;
import br.com.ead.curso.services.ModuloService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuloController {

    @Autowired
    ModuloService moduloService;

    @Autowired
    CursoService coursoService;

    @GetMapping("/modulos")
    public ResponseEntity<List<ModuloModel>> getAllModulos(){
        return ResponseEntity.status(HttpStatus.OK).body(this.moduloService.findAll());
    }

    @GetMapping("/cursos/{idCurso}/modulos")
    public ResponseEntity<List<ModuloModel>> getAllModulos(@PathVariable(value="idCurso") Long idCurso){
        return ResponseEntity.status(HttpStatus.OK).body(this.moduloService.findAllByCurso(idCurso));
    }

    @GetMapping("/cursos/{idCurso}/modulos/{idModulo}")
    public ResponseEntity<Object> getModulo(@PathVariable(value="idCurso") Long idCurso,
                                               @PathVariable(value="idModulo") Long idModulo){
        Optional<ModuloModel> moduleModelOptional = this.moduloService.findModuloIntoCurso(idCurso, idModulo);

        if(moduleModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Módulo não encontrado para o curso informado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(moduleModelOptional.get());
    }

    @PostMapping("/cursos/{idCurso}/modulos")
    public ResponseEntity<Object> saveModulo(@PathVariable(value="idCurso") Long idCurso,
                                             @RequestBody @Valid ModuloDTO moduloDTO){
        Optional<CursoModel> courseModelOptional = this.coursoService.findById(idCurso);

        if(courseModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado.");
        }

        ModuloModel moduloModel = new ModuloModel();
        BeanUtils.copyProperties(moduloDTO, moduloModel);

        moduloModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        moduloModel.setCurso(courseModelOptional.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.moduloService.save(moduloModel));
    }

    @PutMapping("/cursos/{idCurso}/modulos/{idModulo}")
    public ResponseEntity<Object> updateModulo(@PathVariable(value="idCurso") Long idCurso,
                                               @PathVariable(value="idModulo") Long idModulo,
                                               @RequestBody @Valid ModuloDTO moduloDTO){
        Optional<ModuloModel> moduleModelOptional = this.moduloService.findModuloIntoCurso(idCurso, idModulo);

        if(moduleModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Módulo não encontrado para o curso informado.");
        }

        ModuloModel moduloModel = moduleModelOptional.get();
        moduloModel.setTitulo(moduloDTO.getTitulo());
        moduloModel.setDescricao(moduloDTO.getDescricao());

        return ResponseEntity.status(HttpStatus.OK).body(this.moduloService.save(moduloModel));
    }

    @DeleteMapping("/cursos/{idCurso}/modulos/{idModulo}")
    public ResponseEntity<Object> deleteModulo(@PathVariable(value="idCurso") Long idCurso,
                                               @PathVariable(value="idModulo") Long idModulo){
        Optional<ModuloModel> moduleModelOptional = this.moduloService.findModuloIntoCurso(idCurso, idModulo);

        if(moduleModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Módulo não encontrado para o curso informado.");
        }

        this.moduloService.delete(moduleModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Módulo apagado com sucesso.");
    }
}
