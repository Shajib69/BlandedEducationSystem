
package com.coderslab.controller;

import com.coderslab.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(){
        return "/admin/login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String getAuthenticate(Model model, Users users){
        System.out.println("=======" + users.toString());
        return "index";
    }
    
}
