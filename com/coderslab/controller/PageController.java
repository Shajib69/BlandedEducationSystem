package com.coderslab.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String loadHomePage(Model model) {
        model.addAttribute("page", "home_page");
        return "index";
    }

    @RequestMapping("/courses")
    public String loadCoursesPage(Model model) {
        model.addAttribute("page", "courses_page");
        return "index";
    }

    @RequestMapping("/course/{id}")
    public String loadCoursePage(Model model, @PathVariable("id") int id) {
        model.addAttribute("page", "course_page");
        model.addAttribute("courseId", id);
        
        try {
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("username", u.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return "index";
        }
        
    }

    

    @RequestMapping("/contact")
    public String loadContactPage(Model model) {
        model.addAttribute("page", "contact_page");
        return "index";
    }

}
