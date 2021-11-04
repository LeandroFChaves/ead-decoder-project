package br.com.ead.auth.validations.impl;

import br.com.ead.auth.validations.UsuarioConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsuarioConstraintImpl implements ConstraintValidator<UsuarioConstraint, String> {

    @Override
    public void initialize(UsuarioConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String usuario, ConstraintValidatorContext constraintValidatorContext) {
        if (usuario == null || usuario.trim().isEmpty() || usuario.contains(" ")) {
            return false;
        }

        return true;
    }
}
