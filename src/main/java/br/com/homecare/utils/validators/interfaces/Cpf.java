package br.com.homecare.utils.validators.interfaces;

import br.com.homecare.utils.validators.classes.CpfValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = CpfValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cpf {

    String message() default "CPF inválido";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}