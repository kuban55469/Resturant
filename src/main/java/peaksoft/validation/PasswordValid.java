package peacksoft.validation;

import jakarta.validation.Constraint;


import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 23.03.2023
 */

@Constraint(validatedBy = PasswordValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE})
public @interface PasswordValid{

}
