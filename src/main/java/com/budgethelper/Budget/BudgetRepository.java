/* ICS4U Software Development Project
 * 
 * Interface for the repository that holds the Budget records.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Budget;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
	
	List<Budget> findByDate(String date);

}
