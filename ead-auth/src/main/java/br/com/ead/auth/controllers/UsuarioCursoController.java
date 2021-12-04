package br.com.ead.auth.controllers;

import br.com.ead.auth.clients.CursoClient;
import br.com.ead.auth.dtos.CursoDTO;
import br.com.ead.auth.services.UsuarioService;
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
public class UsuarioCursoController {

    @Autowired
    CursoClient cursoClient;

    @Autowired
    UsuarioService userService;

    @GetMapping("/usuarios/{idUsuario}/cursos")
    public ResponseEntity<Page<CursoDTO>> getAllCursosByUsuario(@PathVariable(value = "idUsuario") Long idUsuario,
                                                                @PageableDefault(page = 0, size = 10, sort = "idCurso", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cursoClient.getAllCursosByUsuario(idUsuario, pageable));
    }

}
