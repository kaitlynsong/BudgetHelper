/* ICS4U Software Development Project
 * 
 * Web controller to handle HTTP requests for Income related functions to the application.
 * Also provides the data from the application to display on the web pages.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Income;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class IncomeController {
    
	@Autowired
	private IncomeService incomeservice;

    @GetMapping("/income_page")
    public String viewIncomePage(Model model) {
    	List<Income> listIncome = incomeservice.listAll();
        model.addAttribute("listIncome", listIncome);
        return "Income/income_page";
    }
    
    @GetMapping("/new_income_form")
    public String showIncomeForm(Model model) {
    	Income income = new Income();
        model.addAttribute("income", income);
        return "Income/new_income_form";
    }
    
    @PostMapping("/save_income")
    public String saveIncome(@ModelAttribute("income") @Valid Income income, BindingResult bindingResult) {   
    	//Redirects back to the form if there are errors
    	if (bindingResult.hasErrors()) {
        	return "Income/new_income_form";
        }
    	incomeservice.save(income);
    	return "redirect:/income_page";
    }
    
    @RequestMapping("/edit/income/{id}")
    public ModelAndView showEditIncomeForm(@PathVariable(name = "id") Long id) {
    	ModelAndView mav = new ModelAndView("Income/edit_income_form");	
    	Income income = incomeservice.get(id);
    	mav.addObject("income", income);
        return mav;
    }
    
    @RequestMapping("/delete/income/{id}")
    public String deleteIncome(@PathVariable(name = "id") Long id) {
    	incomeservice.delete(id);
        return "redirect:/income_page";
    }
   
}
