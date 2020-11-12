/* ICS4U Software Development Project
 * 
 * Budget entity model with the id, weeklyBudget, monthlyBudget, and date, for a 
 * specific month of a year.
 * 
 * Hibernate automatically translates this entity into the Budget table in the database
 * with columns id, weekly_budget, monthly_budget, and date.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Budget;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Budget {
    
	private Long id;
	
    @NotNull(message = "Please enter an amount")
    @Min(value = 0, message = "Amount must be zero or greater")
    private double weeklyBudget;
    
    @NotNull(message = "Please enter an amount")
    @Min(value = 0, message = "Amount must be zero or greater")
    private double monthlyBudget;
    
    //Date in the format YYYY-MM
    @NotNull(message = "Please enter a date")
    @Pattern(regexp = "^20\\d\\d-(0[1-9]|1[012])$", message = "Date is invalid")
    @UniqueBudget
    private String date;
    
    //Constructors
    public Budget() {
    	
	}

    protected Budget(double weeklyBudget, double monthlyBudget) {
		this.weeklyBudget = weeklyBudget;
		this.monthlyBudget = monthlyBudget;
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
	
	public double getWeeklyBudget() {
		return weeklyBudget;
	}
	
	public void setWeeklyBudget(double weeklyBudget) {
		this.weeklyBudget = weeklyBudget;
	}
	
	public double getMonthlyBudget() {
		return monthlyBudget;
	}
	
	public void setMonthlyBudget(double monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}