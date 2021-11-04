package br.com.ead.auth.validations;

import br.com.ead.auth.validations.impl.UsuarioConstraintImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsuarioConstraintImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsuarioConstraint {
    String message() default "Usuário inválido!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
