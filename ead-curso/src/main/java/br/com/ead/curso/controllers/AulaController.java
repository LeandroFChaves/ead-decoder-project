package br.com.ead.curso.controllers;

import br.com.ead.curso.dtos.AulaDTO;
import br.com.ead.curso.models.AulaModel;
import br.com.ead.curso.models.ModuloModel;
import br.com.ead.curso.services.AulaService;
import br.com.ead.curso.services.ModuloService;
import br.com.ead.curso.specifications.SpecificationTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AulaController {

    @Autowired
    AulaService aulaService;

    @Autowired
    ModuloService moduloService;

    @GetMapping("/aulas")
    public ResponseEntity<Page<AulaModel>> getAllAulas(SpecificationTemplate.AulaSpec spec,
                                                       @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.aulaService.findAll(spec, pageable));
    }

    @GetMapping("/modulos/{idModulo}/aulas")
    public ResponseEntity<Page<AulaModel>> getAllAulas(@PathVariable(value = "idModulo") Long idModulo,
                                                       SpecificationTemplate.AulaSpec spec,
                                                       @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.aulaService.findAllByModulo(SpecificationTemplate.aulaModuloId(idModulo).and(spec), pageable));
    }

    @GetMapping("/modulos/{idModulo}/aulas/{idAula}")
    public ResponseEntity<Object> getOneAula(@PathVariable(value = "idModulo") Long idModulo,
                                             @PathVariable(value = "idAula") Long idAula) {
        Optional<AulaModel> aulaModelOptional = this.aulaService.findAulaIntoModulo(idModulo, idAula);

        if (aulaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aula não encontrada para esse módulo.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(aulaModelOptional.get());
    }

    @PostMapping("/modulos/{idModulo}/aulas")
    public ResponseEntity<Object> saveAula(@PathVariable(value = "idModulo") Long idModulo,
                                           @RequestBody @Valid AulaDTO aulaDTO) {
        Optional<ModuloModel> moduloModelOptional = this.moduloService.findById(idModulo);

        if (moduloModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Módulo não encontrado.");
        }

        AulaModel aulaModel = new AulaModel();
        BeanUtils.copyProperties(aulaDTO, aulaModel);

        aulaModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        aulaModel.setModulo(moduloModelOptional.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.aulaService.save(aulaModel));
    }

    @PutMapping("/modulos/{idModulo}/aulas/{idAula}")
    public ResponseEntity<Object> updateAula(@PathVariable(value = "idModulo") Long idModulo,
                                             @PathVariable(value = "idAula") Long idAula,
                                             @RequestBody @Valid AulaDTO aulaDTO) {
        Optional<AulaModel> aulaModelOptional = this.aulaService.findAulaIntoModulo(idModulo, idAula);

        if (aulaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aula não encontrada para esse módulo.");
        }

        AulaModel aulaModel = aulaModelOptional.get();
        aulaModel.setTitulo(aulaDTO.getTitulo());
        aulaModel.setDescricao(aulaDTO.getDescricao());
        aulaModel.setVideoUrl(aulaDTO.getVideoUrl());

        return ResponseEntity.status(HttpStatus.OK).body(this.aulaService.save(aulaModel));
    }

    @DeleteMapping("/modulos/{idModulo}/aulas/{idAula}")
    public ResponseEntity<Object> deleteAula(@PathVariable(value = "idModulo") Long idModulo,
                                             @PathVariable(value = "idAula") Long idAula) {
        Optional<AulaModel> aulaModelOptional = this.aulaService.findAulaIntoModulo(idModulo, idAula);

        if (aulaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aula não encontrada para esse módulo.");
        }

        this.aulaService.delete(aulaModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Aula apagado com sucesso.");
    }

}
