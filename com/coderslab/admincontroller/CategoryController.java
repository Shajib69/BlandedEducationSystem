
package com.coderslab.admincontroller;

import com.coderslab.dao.CategoryDao;
import com.coderslab.dao.CourseDao;
import com.coderslab.entity.Category;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class CategoryController {
    
    @Autowired
    private CategoryDao categoryDao;
    
    @RequestMapping(value = "/add_category", method = RequestMethod.GET)
    public String loadAddCategory(Model model, HttpServletRequest request){
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/add_category";
    }
    
    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public String saveCategory(Model model, Category category){
        System.out.println(category.toString());
        try {
            boolean status = categoryDao.saveData(category);
            if(status){
                model.addAttribute("sm", "Category Create Successfully");
            }
        } catch (Exception ex) {
            model.addAttribute("em", "Category Not Created");
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/admin/add_category";
    }
    
    @RequestMapping(value = "/list_category", method = RequestMethod.GET)
    public String loadManageCategory(Model model, HttpServletRequest request){
        model.addAttribute("categories", categoryDao.getAllData(Category.class));
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/list_category";
    }
    
    @RequestMapping(value = "/update_category_status/{id}/{status}", method = RequestMethod.GET)
    public String updateCategoryStatus(@PathVariable("id") int id, @PathVariable("status") boolean status, Model model){
        try {
            Category c = categoryDao.findData(Category.class, id);
            c.setStatus(!status);
            categoryDao.updateData(c);
            model.addAttribute("sm", "Status Update Successfull");
        } catch (Exception ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/admin/list_category";
    }
    
    @RequestMapping(value = "/edit_category/{id}", method = RequestMethod.GET)
    public String editCategory(@PathVariable("id") int id, Model model, HttpServletRequest request){
        try {
            model.addAttribute("category", categoryDao.findData(Category.class, id));
        } catch (Exception ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_category";
    }
    
    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    public String updateCategory(Category category, Model model){
        try {
            Category c = categoryDao.findData(Category.class, category.getCategoryId());
            c.setCategoryName(category.getCategoryName());
            c.setDescription(category.getDescription());
            categoryDao.updateData(c);
            model.addAttribute("sm", "category info update successfully");
        } catch (Exception ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/admin/edit_category/" + category.getCategoryId();
    }
    
    @RequestMapping(value = "/delete_category/{id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("id") int id, Model model){
        
        try {
            categoryDao.deleteData(categoryDao.findData(Category.class, id));
            model.addAttribute("sm", "Category deleted successfully");
        } catch (Exception ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/admin/list_category";
    }
    
    
}
