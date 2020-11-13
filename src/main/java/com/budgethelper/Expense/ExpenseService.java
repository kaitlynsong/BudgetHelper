/* ICS4U Software Development Project
 * 
 * Service class for Expense that delegates all the calls to ExpenseRepository. Also provides some
 * helper functions for complicated business logic. 
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Expense;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
		
	@Autowired
	private ExpenseRepository expenseRepo;
	
	public List<Expense> listAll() {
		return expenseRepo.findAll();
	}
	
	public int getDaysInMonth(String budgetMonth) {
		//year
		String yearString = budgetMonth.substring(0,4);
		int yearInt = Integer.parseInt(yearString);
		
		//month
		String monthString = budgetMonth.substring(5,7);
		int monthInt = Integer.parseInt(monthString);
		
		//Number of days in the month that year
		YearMonth yearMonthObject = YearMonth.of(yearInt, monthInt);
		int daysInMonth = yearMonthObject.lengthOfMonth();  
		
		return daysInMonth;
	}
	
	public HashMap<String, Object> getSelectedMonthExpenses(String budgetMonth) {
		int daysInMonth = getDaysInMonth(budgetMonth);
		ArrayList<String> categoryNames = getMonthCategories(budgetMonth);
		HashMap<String, Object> expenseMap = new HashMap<>();
		HashMap<String, Double> categoryNoData = new HashMap<>();

		//Set HashMap for dates without expenses
		for (int k = 0; k < categoryNames.size(); k++) { 
			String category = categoryNames.get(k);
			categoryNoData.put(category, 0.0);
		}
		
		HashMap<String, Double> categorySubtotals = new HashMap<>();
		categorySubtotals.putAll(categoryNoData);	
		
		//To loop through each day of that month
		for (int i = 1; i <= daysInMonth; i++) {
			
			String dayString = Integer.toString(i);
			//Dates where the day is a single digit
			if (i < 10) {
				dayString = "0" + dayString;
			}
			String date = budgetMonth + "-" + dayString;
			
			List<Expense> expenseEntries = expenseRepo.findByDate(date);
			
			//If there's no data for that date
			if (expenseEntries.isEmpty()) {
		    	expenseMap.put(date, categoryNoData);
			}
			
			//For dates with expense entries
			else {
				//Initialize a HashMap to hold all of the categories and set the amounts to zero
				HashMap<String, Double> categoryData = new HashMap<>();
				categoryData.putAll(categoryNoData);		
				
				//Loop through all of the expenses for that day, get the category and amount,
				//and add them to the HashMaps
				for (int j = 0; j < expenseEntries.size(); j++) { 
					String category = expenseEntries.get(j).getCategory();
					Double amount = expenseEntries.get(j).getAmount();						
					categoryData.put(category, categoryData.get(category) + amount);
					categorySubtotals.put(category, categorySubtotals.get(category) + amount);
				}
			
				//Put the HashMaps into a larger one
				expenseMap.put(date, categoryData);
			}
		
		}

		expenseMap.put("Subtotals", categorySubtotals);
		
		return expenseMap;
	}
	
	public ArrayList<String> getDateList(String budgetMonth) {
		int daysInMonth = getDaysInMonth(budgetMonth);
		ArrayList<String> dateList = new ArrayList<String>();

		//To loop through each day of that month
		for (int i = 1; i <= daysInMonth; i++) {
			
			String dayString = Integer.toString(i);
			
			if (i < 10) {
				dayString = "0" + dayString;
			}
			
			String date = budgetMonth + "-" + dayString;
			dateList.add(date);
		}
		
		return dateList;
	}
	
	
	public ArrayList<String> getMonthCategories(String budgetMonth) {
		List<Expense> expenseEntries = expenseRepo.findByDateStartingWith(budgetMonth);
		ArrayList<String> categories = new ArrayList<String>();
		
		//Go through each expense object in the list
		for (int i = 0; i < expenseEntries.size(); i++) { 
			
			//Get the category
			String category = expenseEntries.get(i).getCategory();
							  
	        // If this category is not present in the new categories list, 
			// then add it
	        if (!categories.contains(category)) {
	                categories.add(category); 
	        } 
		}
		//Sort the categories in alphabetical order
		Collections.sort(categories);
		return(categories);
	}
	
	public Double getTotalExpenses(String budgetMonth) {
		List<Expense> expenseEntries = expenseRepo.findByDateStartingWith(budgetMonth);
		Double totalExpenses = 0.0;

		//Go through each expense entry for the month
		for (int i = 0; i < expenseEntries.size(); i++) { 
			//Get the amount from each entry and add it to the total
			Double expenses = expenseEntries.get(i).getAmount();
			totalExpenses = totalExpenses + expenses;
		}
		
		return totalExpenses;
	}
	
	public Double getAvgWeeklyExpenses(String budgetMonth) {
		Double totalExpenses = getTotalExpenses(budgetMonth);
		int daysInMonth = getDaysInMonth(budgetMonth);  
		
		//Calculate the average amount of money spent over 7 days for the month
		Double avgWeeklyExpenses = totalExpenses / daysInMonth * 7;
		
		//Round the amount to have two decimal places
		Double roundedAvgWeeklyExpenses = Math.round(avgWeeklyExpenses * 100.0) / 100.0;
		
		return roundedAvgWeeklyExpenses;
	}
	
	public List<Object> getDailyExpenseTotals(String budgetMonth) throws ParseException {
		//Get the number of weeks in the month
		int maxNumOfWeeksInMonth = getNumOfWeeksInMonth(budgetMonth);
		final int maxNumOfDaysInWeek = 7;
		//First dimension to hold the week of the month
		//Second dimension to hold the day of the week
		HashMap<Integer, Double>[][] expenseCalendar = new HashMap[maxNumOfWeeksInMonth][maxNumOfDaysInWeek];
		int dayOfWeek, weekOfMonth;

		HashMap<Integer, Double> dayOfTheWeekTotals = new HashMap<>();
		for (int m = 0; m < 7; m++) {
			dayOfTheWeekTotals.put(m, 0.0);
		}
		
		//Get the number of days in the month
		int daysInMonth = getDaysInMonth(budgetMonth);
	
		
		//To loop through each day of that month
		for (int i = 1; i <= daysInMonth; i++) {
			
			String dayString = Integer.toString(i);
			//Dates where the day is a single digit
			if (i < 10) {
				dayString = "0" + dayString;
			}
			String date = budgetMonth + "-" + dayString;
			
			//Get the day of week and week of month for the date
			Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);  
			Calendar cal = Calendar.getInstance();
		    cal.setTime(newDate);
		    //Subtract 1 since it starts at 1
		    //Want it to match the array's index since it will be used as the indexes
		    dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;  
		    weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH) - 1;
		    
		    //Instantiation of the HashMap object
		    expenseCalendar[weekOfMonth][dayOfWeek] = new HashMap<>();
			
			List<Expense> expenseEntries = expenseRepo.findByDate(date);
			
			
			//If there's no data for that date
			if (expenseEntries.isEmpty()) {
				expenseCalendar[weekOfMonth][dayOfWeek].put(i, 0.0);
			}
			
			//For dates with expense entries
			else {
				//Loop through all of the expenses for that day, get the amount,
				//and add them to the HashMap
				Double amount = 0.0;
				for (int j = 0; j < expenseEntries.size(); j++) { 
					amount += expenseEntries.get(j).getAmount();										
				}
				expenseCalendar[weekOfMonth][dayOfWeek].put(i, amount);
				dayOfTheWeekTotals.put(dayOfWeek, dayOfTheWeekTotals.get(dayOfWeek) + amount);
			}
		
		}
		
		return Arrays.asList(expenseCalendar, dayOfTheWeekTotals);
	}
	
	public int getNumOfWeeksInMonth(String budgetMonth) throws ParseException {
		Date newDate = new SimpleDateFormat("yyyy-MM").parse(budgetMonth);  
		Calendar cal = Calendar.getInstance();
	    cal.setTime(newDate);
	    // Return the total number of weeks in the month
        return cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}
	
	public void save(Expense expense) {
		//When no category is given, set as Other
		if (expense.getCategory() == null) {
			expense.setCategory("Other");
		}
		expenseRepo.save(expense);
	}
	
	public Expense get(Long id) {
		return expenseRepo.findById(id).get();
	}
	
	public void delete(Long id) {
		expenseRepo.deleteById(id);
	}	
}
