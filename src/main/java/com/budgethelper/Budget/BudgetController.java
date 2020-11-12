/* ICS4U Software Development Project
 * 
 * Controller to handle HTTP requests for Budget related functions to the application.
 * 
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Budget;

import java.util.*;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class BudgetController {
    
	@Autowired
    private BudgetService budgetservice;
	
    @GetMapping("/index")
    public String viewHomePage(Model model) {      
        return "index";
    }
    
    @GetMapping("/budget_page")
    public String viewBudgetPage(Model model) {
    	List<Budget> listCurrentBudget = budgetservice.getCurrentBudget();
        model.addAttribute("listCurrentBudget", listCurrentBudget);
        return "Budget/budget_page";
    }
    
    @GetMapping("/view_all_budgets")
    public String viewAllBudgets(Model model) {
    	List<Budget> listBudgets = budgetservice.listAll();
        model.addAttribute("listBudgets", listBudgets);
        return "Budget/view_all_budgets";
    }
    
    @GetMapping("/new_budget_form")
    public String showBudgetForm(Model model) {
    	Budget budget = new Budget();
        model.addAttribute("budget", budget);
        return "Budget/new_budget_form";
    }
    
    @PostMapping("/save_budget")
    public String saveBudget(@ModelAttribute("budget") @Valid Budget budget, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        	return "Budget/new_budget_form";
        }
    	budgetservice.save(budget);
    	return "redirect:/view_all_budgets";
    }
    
    @RequestMapping("/edit/budget/{id}")
    public ModelAndView showEditBudgetForm(@PathVariable(name = "id") Long id) {
    	ModelAndView mav = new ModelAndView("Budget/edit_budget_form");	
    	Budget budget = budgetservice.get(id);
    	mav.addObject("budget", budget);
        return mav;
    }
    
    @RequestMapping("/delete/budget/{id}")
    public String deleteBudget(@PathVariable(name = "id") Long id) {
    	budgetservice.delete(id);
        return "redirect:/view_all_budgets";
    }
     
}
