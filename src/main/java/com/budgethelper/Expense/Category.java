/* ICS4U Software Development Project
 * 
 * Category entity model with id and categoryName. This will be used in the Expense model
 * for the different category types for expenses.
 * 
 * Hibernate automatically translates this entity into the Category table in the database
 * with columns id and category_name.
 * 
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Expense;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category {

	private Long id;
	
    @NotNull(message = "Please enter a name")
    @UniqueCategory
    @Size(max=20, message = "Entered category name is too long")
	private String categoryName;

    //Constructor
	public Category() {
	}
    
    //Getters and setters
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
