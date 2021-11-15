package br.com.ead.curso.validations;

import br.com.ead.curso.clients.UsuarioClient;
import br.com.ead.curso.dtos.CursoDTO;
import br.com.ead.curso.dtos.UsuarioDTO;
import br.com.ead.curso.enums.UsuarioTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.client.HttpStatusCodeException;

@Component
public class CursoValidator implements Validator {

    @Autowired
    @Qualifier("defaultValidator")
    private Validator validator;

    @Autowired
    UsuarioClient usuarioClient;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        CursoDTO cursoDTO = (CursoDTO) o;
        validator.validate(cursoDTO, errors);

        if (!errors.hasErrors()) {
            validateUsuarioDocente(cursoDTO.getUsuarioDocente(), errors);
        }
    }

    private void validateUsuarioDocente(Long usuarioDocente, Errors errors) {
        ResponseEntity<UsuarioDTO> responseUserInstructor;

        try {
            responseUserInstructor = this.usuarioClient.getOneUsuarioById(usuarioDocente);
            if (responseUserInstructor.getBody().getTipo().equals(UsuarioTipo.ALUNO)) {
                errors.rejectValue("usuarioDocente", "UsuarioDocenteError", "Usuário deve ser um DOCENTE ou ADMIN.");
            }
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                errors.rejectValue("usuarioDocente", "UsuarioDocenteError", "Docente não encontrado.");
            }
        }
    }
}
