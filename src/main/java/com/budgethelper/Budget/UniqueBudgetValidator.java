/* ICS4U Software Development Project
 * 
 * Implementation of the UniqueBudget validator.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueBudgetValidator implements ConstraintValidator<UniqueBudget, String> {

    @Autowired
    public BudgetRepository budgetRepo;

    @Override
    public boolean isValid(String budget, ConstraintValidatorContext context) {
    	return budgetRepo.findByDate(budget).isEmpty();
    }
}