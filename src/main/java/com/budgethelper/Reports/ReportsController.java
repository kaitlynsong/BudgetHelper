/* ICS4U Software Development Project
 * 
 * Web controller to handle HTTP requests for Reports related functions to the application.
 * Also provides the data from the application to display on the web pages.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Reports;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.budgethelper.Budget.Budget;
import com.budgethelper.Budget.BudgetService;
import com.budgethelper.Expense.ExpenseService;
import com.budgethelper.Income.IncomeService;
 
@Controller
public class ReportsController {
	
	@Autowired
    private BudgetService budgetservice;
	
	@Autowired
    private ExpenseService expenseservice;
	
	@Autowired
    private IncomeService incomeservice;
	
	
    @GetMapping("/reports_page")
     public String viewReportsPage(Model model) {  
    	List<Budget> listBudgets = budgetservice.listAll();
        model.addAttribute("listBudgets", listBudgets);
        Budget budget = new Budget();
        model.addAttribute("budget", budget);
        return "Reports/reports_page";    
    }
    
    @PostMapping("/view_reports_page")
    public String viewMonthlyReport(@ModelAttribute("budget") Budget budget, Model model) throws ParseException { 
         
    	 //For the Category Expense Table
    	 ArrayList<String> listCategories = expenseservice.getMonthCategories(budget.getDate());    
         model.addAttribute("listCategories", listCategories);
         
         HashMap<String, Object> listExpenses = expenseservice.getSelectedMonthExpenses(budget.getDate());
         model.addAttribute("listExpenses", listExpenses);
         
         ArrayList<String> dateList = expenseservice.getDateList(budget.getDate());    
         model.addAttribute("dateList", dateList);

         HashMap<String, Double> subTotalMap = (HashMap<String, Double>) listExpenses.get("Subtotals");
 		 String largestCategory = Collections.max(subTotalMap.entrySet(), Map.Entry.comparingByValue()).getKey();
 		 model.addAttribute("largestCategory", largestCategory);
         
 		 //For the Budget Status
         List<Budget> listCurrentBudget = budgetservice.getCurrentBudget();
         model.addAttribute("listCurrentBudget", listCurrentBudget);
         
         Double avgWeeklyExpenses = expenseservice.getAvgWeeklyExpenses(budget.getDate());
         model.addAttribute("avgWeeklyExpenses", avgWeeklyExpenses);
         
         Double totalExpenses = expenseservice.getTotalExpenses(budget.getDate());
         model.addAttribute("totalExpenses", totalExpenses);
         
         //Total Income
         Double totalIncome = incomeservice.getTotalIncome(budget.getDate());
         model.addAttribute("totalIncome", totalIncome);
         
         //For the Heaviest Spending Day of the Week
         List<Object> dailyExpenseTotals = expenseservice.getDailyExpenseTotals(budget.getDate());
         model.addAttribute("dailyExpenseTotals", dailyExpenseTotals.get(0));
         model.addAttribute("dayOfTheWeekTotals", dailyExpenseTotals.get(1));
         
         HashMap<Integer, Double> hashMap2 = (HashMap<Integer, Double>) dailyExpenseTotals.get(1);
         Integer heaviestSpendingDayOfTheWeek = Collections.max(hashMap2.entrySet(), Map.Entry.comparingByValue()).getKey();
         model.addAttribute("heaviestSpendingDayOfTheWeek", heaviestSpendingDayOfTheWeek);
 		 
         String dayOfWeek = DayOfWeek.of(heaviestSpendingDayOfTheWeek).toString();
         model.addAttribute("dayOfWeek", dayOfWeek);
                 
         return "Reports/view_reports_page";    
   }
     
}
