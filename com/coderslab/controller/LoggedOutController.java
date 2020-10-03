
package com.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoggedOutController {

    @RequestMapping(value = "/loggedout", method = RequestMethod.GET)
    public String makeLoggedOut(Model model){
        model.addAttribute("page", "home_page");
        model.addAttribute("sm", "You are successfully logged out");
        return "index";
    }
    
}
