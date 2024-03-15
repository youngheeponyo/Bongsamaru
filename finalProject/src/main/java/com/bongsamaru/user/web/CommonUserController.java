package com.bongsamaru.user.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class CommonUserController {
	
	
	 @GetMapping("/login")
	    public String loginForm(Model model){
		 
		 
	        return "login/FacilityLogin";
	    }
	

	
	
}


