/* ICS4U Software Development Project
 * 
 * UniqueCategory validator interface.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Expense;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCategoryValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCategory {
    String message() default "Category already exists";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}