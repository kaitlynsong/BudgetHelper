/* ICS4U Software Development Project
 * 
 * Implementation of the UniqueCategory validator.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory, String> {

    @Autowired
    public CategoryRepository categoryRepo;

    @Override
    public boolean isValid(String category, ConstraintValidatorContext context) {
    	return categoryRepo.findByCategoryName(category).isEmpty();
    }
}