/* ICS4U Software Development Project
 * 
 * Service class for Budget that delegates all the calls to BudgetRepository. Also provides some
 * helper functions for complicated business logic. 
 *  
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Budget;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BudgetService {
	
	@Autowired
	private BudgetRepository budgetRepo;
	
	public List<Budget> listAll() {
		return budgetRepo.findAll();
	}
	
	//Determines the current date and gets the budget for the current month
	public List<Budget> getCurrentBudget() {
		LocalDate currentDate = LocalDate.now();
		String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
		return budgetRepo.findByDate(formattedDate);
	}
	
	public void save(Budget budget) {
		budgetRepo.save(budget);
	}
	
	public Budget get(Long id) {
		return budgetRepo.findById(id).get();
	}
	
	public void delete(Long id) {
		budgetRepo.deleteById(id);
	}	
}