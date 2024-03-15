package fr.ekwateur.facturation.shared.validators;

import fr.ekwateur.facturation.shared.validators.impl.ClientRefConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClientRefConstraintValidator.class)

public @interface ClientRefConstraint {
    String message() default "la référence du client est non valide";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
