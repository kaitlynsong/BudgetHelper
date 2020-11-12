/* ICS4U Software Development Project
 * 
 * Interface for the repository that holds the Income records.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Income;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
	
	List<Income> findByDateStartingWith(String prefix);
	List<Income> findByDate(String date);
	
}
