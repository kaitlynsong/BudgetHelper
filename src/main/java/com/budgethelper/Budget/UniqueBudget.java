/* ICS4U Software Development Project
 * 
 * UniqueBudget validator interface.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Budget;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueBudgetValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueBudget {
    String message() default "There is already a budget created for this month";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}