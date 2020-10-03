package com.coderslab.controller;

import com.coderslab.dao.UserRoleDao;
import com.coderslab.dao.UsersDao;
import com.coderslab.entity.Authorities;
import com.coderslab.entity.UserRole;
import com.coderslab.entity.Users;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @InitBinder
    public void myInitBiner(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(format, false));
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String loadRegisterPage(Model model, HttpServletRequest request){
        model.addAttribute("userRoles", userRoleDao.getAllData(UserRole.class));
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        model.addAttribute("page", "register_page");
        return "index";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerCustomer(Model model, @Valid Users users, BindingResult result){
        
        if(users.getUserType().equals("ROLE_USER")){
            users.setEnabled(true);
        }else{
            users.setEnabled(false);
        }
        
        users.setAuthority(users.getUserType());
        users.setRegisterDate(new Date());
        
        //set this user into authority table
        Authorities authorities = new Authorities();
        authorities.setAuthority(users.getAuthority());
        authorities.setUsername(users.getUsername());
        
        System.out.println("======" + users.toString());
        System.out.println("======" + authorities.toString());
        
        
        try {
            boolean status = usersDao.saveUserWithAuthority(users, authorities);
            if(status){
                model.addAttribute("sm", "Regster successfull");
            }else{
                model.addAttribute("em", "Register not successfull");
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        return "redirect:/register";
    }
    
}
