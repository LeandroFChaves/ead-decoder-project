package br.com.ead.curso.controllers;

import br.com.ead.curso.clients.UsuarioClient;
import br.com.ead.curso.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CursoUsuarioController {

    @Autowired
    UsuarioClient usuarioClient;

    @GetMapping("/cursos/{idCurso}/usuarios")
    public ResponseEntity<Page<UsuarioDTO>> getAllUsuariosByCurso(@PathVariable(value = "idCurso") Long idCurso,
                                                                  @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioClient.getAllUsuariosByCurso(idCurso, pageable));
    }
}
