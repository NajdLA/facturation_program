package fr.ekwateur.facturation.shared.validators.impl;

import fr.ekwateur.facturation.shared.validators.ClientRefConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
public class ClientRefConstraintValidator implements ConstraintValidator<ClientRefConstraint, String> {

    private static final String REGEX_REF_CLIENT = "^EKW\\d{8}$";

    @Override
    public boolean isValid(String clientRef, ConstraintValidatorContext constraintValidatorContext) {
        return !clientRef.isBlank() && Pattern.matches(REGEX_REF_CLIENT, clientRef);
    }
}
