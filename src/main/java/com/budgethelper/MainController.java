package com.budgethelper;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
 
@Controller
public class MainController {
     
    @GetMapping("/register")
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
         
        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);
         
        return "register_form";
    }
     
    @GetMapping("/index")
    public String toHome(Model model) {      
        return "index";
    }
    
    @GetMapping("/set_budget_page")
    public String toSetBudget(Model model) {      
        return "set_budget_page";
    }
    
    @GetMapping("/income_page")
    public String toIncome(Model model) {      
        return "income_page";
    }
    
    @GetMapping("/expense_page")
    public String toExpense(Model model) {      
        return "expense_page";
    }
        
    @GetMapping("/reports_page")
     public String toReports(Model model) {      
        return "reports_page";    
    }
    
    @PostMapping("/register")
    public String submitForm(@ModelAttribute("user") User user) {
        System.out.println(user);
        return "register_success";
    }
}
