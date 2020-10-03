
package com.coderslab.admincontroller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadIndexPage(HttpServletRequest request, Model model){
//        String userId = (String) request.getSession().getAttribute("userId");
//        String roleId = (String) request.getSession().getAttribute("roleId");
//        if(userId == null && roleId == null){
//            return "redirect:/admin/login";
//        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("userName", u.getUsername());
        return "/admin/index";
    }
    
  
    
    
    
    @RequestMapping(value = "/t", method = RequestMethod.GET)
    public String loadMainTemplatePage(){
        return "/admin/template_main";
    }
    
    
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLoginPage(){
        return "/admin/login";
    }
    
    
    
    
}
