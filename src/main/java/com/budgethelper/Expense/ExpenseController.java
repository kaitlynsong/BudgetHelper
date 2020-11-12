/* ICS4U Software Development Project
 * 
 * Controller to handle HTTP requests for Expense and Category related functions to the application.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Expense;

import java.util.*;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExpenseController {
    
	@Autowired
    private ExpenseService expenseservice;

    @GetMapping("/expense_page")
    public String viewExpensePage(Model model) {  
    	List<Expense> listExpenses = expenseservice.listAll();
        model.addAttribute("listExpenses", listExpenses);
        return "Expense/expense_page";
    }
    

    @GetMapping("/new_expense_form")
    public String showExpenseForm(Model model) {
    	Expense expense = new Expense();
        model.addAttribute("expense", expense);
        List<Category> listCategories = categoryservice.listAll();
        model.addAttribute("listCategories", listCategories);
        return "Expense/new_expense_form";
    }
    
    @PostMapping("/save_expense")
    public String saveExpense(@ModelAttribute("expense") @Valid Expense expense, BindingResult bindingResult, Model model) {   
    	if (bindingResult.hasErrors()) {
    		List<Category> listCategories = categoryservice.listAll();
            model.addAttribute("listCategories", listCategories);
    		return "Expense/new_expense_form";
        }
    	expenseservice.save(expense);
    	return "redirect:/expense_page";
    }
    
    @RequestMapping("/edit/expense/{id}")
    public ModelAndView showEditExpenseForm(@PathVariable(name = "id") Long id, Model model) {
    	ModelAndView mav = new ModelAndView("Expense/edit_expense_form");	
    	Expense expense = expenseservice.get(id);
    	mav.addObject("expense", expense);
    	List<Category> listCategories = categoryservice.listAll();
        model.addAttribute("listCategories", listCategories);
        return mav;
    }
    
    @RequestMapping("/delete/expense/{id}")
    public String deleteExpense(@PathVariable(name = "id") Long id) {
    	expenseservice.delete(id);
        return "redirect:/expense_page";
    }
   
    
    
    
    @Autowired
    private CategoryService categoryservice;
    
    @GetMapping("/category_page")
    public String viewCategoryPage(Model model) {  
    	List<Category> listCategories = categoryservice.listAll();
        model.addAttribute("listCategories", listCategories);
        return "Expense/category_page";
    }
    
    @GetMapping("/new_category_form")
    public String showCategoryForm(Model model) {
    	Category category = new Category();
        model.addAttribute("category", category);
        return "Expense/new_category_form";
    }
    
    @PostMapping("/save_category")
    public String saveCategory(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult) {   
    	if (bindingResult.hasErrors()) {
        	return "Expense/new_category_form";
        }
    	categoryservice.save(category);
    	return "redirect:/category_page";
    }
    
    @RequestMapping("/edit/category/{id}")
    public ModelAndView showEditCategoryForm(@PathVariable(name = "id") Long id) {
    	ModelAndView mav = new ModelAndView("Expense/edit_category_form");	
    	Category category = categoryservice.get(id);
    	mav.addObject("category", category);
        return mav;
    }
    
    @RequestMapping("/delete/category/{id}")
    public String deleteCategory(@PathVariable(name = "id") Long id) {
    	categoryservice.delete(id);
        return "redirect:/category_page";
    }
}
