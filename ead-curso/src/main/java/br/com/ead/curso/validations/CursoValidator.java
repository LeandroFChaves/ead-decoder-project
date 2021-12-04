package br.com.ead.curso.validations;

import br.com.ead.curso.dtos.CursoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CursoValidator implements Validator {

    @Autowired
    @Qualifier("defaultValidator")
    private Validator validator;

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
        /*
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
        */
    }
}
