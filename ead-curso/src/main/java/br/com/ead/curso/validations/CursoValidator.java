package br.com.ead.curso.validations;

import br.com.ead.curso.dtos.CursoDTO;
import br.com.ead.curso.enums.UsuarioTipo;
import br.com.ead.curso.models.UsuarioModel;
import br.com.ead.curso.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CursoValidator implements Validator {

    @Autowired
    @Qualifier("defaultValidator")
    private Validator validator;

    @Autowired
    UsuarioService usuarioService;

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
        Optional<UsuarioModel> userModelOptional = this.usuarioService.findById(usuarioDocente);

        if (userModelOptional.isEmpty()) {
            errors.rejectValue("usuarioDocente", "UsuarioDocenteError", "Docente não encontrado.");
        }

        if (userModelOptional.isPresent() && userModelOptional.get().getTipo().equals(UsuarioTipo.ALUNO.toString())) {
            errors.rejectValue("usuarioDocente", "UsuarioDocenteError", "Usuário deve ser um DOCENTE ou ADMIN para criar um novo curso.");
        }
    }
}
