/* ICS4U Software Development Project
 * 
 * Interface for the repository that holds the Expense records.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Expense;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
	List<Expense> findByDate(String date);
	List<Expense> findByDateStartingWith(String prefix);
	
}

