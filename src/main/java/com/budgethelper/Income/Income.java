/* ICS4U Software Development Project
 * 
 * Income entity model with the id, date, amount, and note.
 * 
 * Hibernate automatically translates this entity into the Income table in the database
 * with columns id, date, amount, and note.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Income;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Income {
    
	private Long id;
	
	//Date in the format YYYY-MM-DD
    @NotNull(message = "Please enter a date")
    @Pattern(regexp = "^20\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Date is invalid")
    private String date;
    
    @NotNull(message = "Please enter an amount")
    @Min(value = 0, message = "Amount must be zero or greater")
    private double amount;
    
    private String note;
    

	//Constructor
    protected Income() {
    	
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
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}