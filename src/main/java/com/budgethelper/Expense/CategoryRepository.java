/* ICS4U Software Development Project
 * 
 * Interface for the repository that holds the Category records.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Expense;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByCategoryName(String categoryName);

}
