
package com.coderslab.admincontroller;

import com.coderslab.dao.CategoryDao;
import com.coderslab.dao.SubCategoryDao;
import com.coderslab.entity.Category;
import com.coderslab.entity.SubCategory;
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
public class SubCategoryController {
    @Autowired
    private SubCategoryDao subCategoryDao;
    @Autowired
    private CategoryDao categoryDao;
    
    @RequestMapping(value = "/add_sub_category", method = RequestMethod.GET)
    public String loadAddSubCategory(Model model, HttpServletRequest request){
        model.addAttribute("categories", categoryDao.getAllData(Category.class));
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/add_sub_category";
    }
    
    @RequestMapping(value = "/saveSubCategory", method = RequestMethod.POST)
    public String saveSubCategory(SubCategory subCategory, Model model){
        if(subCategory.getCategoryId() == 0){
            model.addAttribute("em", "Select Category Name");
            return "redirect:/admin/add_sub_category";
        }
        
        try {
            boolean status = subCategoryDao.saveData(subCategory);
            model.addAttribute("sm", "Sub Category info saved successfully");
        } catch (Exception ex) {
            Logger.getLogger(SubCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/admin/add_sub_category";
    }
    
    @RequestMapping(value = "/list_sub_category", method = RequestMethod.GET)
    public String loadListSubCategory(Model model, HttpServletRequest request){
        model.addAttribute("subCategoires", subCategoryDao.getAllData(SubCategory.class));
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/list_sub_category";
    }
    
    @RequestMapping(value = "/update_sub_category_status/{id}/{status}", method = RequestMethod.GET)
    public String updateSubCategoryStatus(@PathVariable("id") int id, @PathVariable("status") boolean status, Model model){
        try {
            SubCategory sc = subCategoryDao.findData(SubCategory.class, id);
            sc.setStatus(!status);
            subCategoryDao.updateData(sc);
            model.addAttribute("sm", "Status update successfull");
        } catch (Exception ex) {
            Logger.getLogger(SubCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/admin/list_sub_category";
    }
    
    @RequestMapping(value = "/edit_sub_category/{id}", method = RequestMethod.GET)
    public String editSubCategory(@PathVariable("id") int id, Model model, HttpServletRequest request){
        try {
            model.addAttribute("subCategory", subCategoryDao.findData(SubCategory.class, id));
        } catch (Exception ex) {
            Logger.getLogger(SubCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("categories", categoryDao.getAllData(Category.class));
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_sub_category";
    }
    
    @RequestMapping(value = "/updateSubCategory", method = RequestMethod.POST)
    public String updateSubCategory(Model model, SubCategory subCategory){
        
        try {
            SubCategory sc = subCategoryDao.findData(SubCategory.class, subCategory.getSubCategoryId());
            sc.setDescription(subCategory.getDescription());
            sc.setSubCategoryName(subCategory.getSubCategoryName());
            sc.setCategoryId(subCategory.getCategoryId());
            subCategoryDao.updateData(sc);
            model.addAttribute("sm", "Sub Category Inof Update successfull");
        } catch (Exception ex) {
            Logger.getLogger(SubCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/admin/edit_sub_category/" + subCategory.getSubCategoryId();
    }
    
    @RequestMapping(value = "/delete_sub_category/{id}")
    public String deleteSubCategory(Model model, @PathVariable("id") int id){
        
        try {
            SubCategory sc =  subCategoryDao.findData(SubCategory.class, id);
            subCategoryDao.deleteData(sc);
            model.addAttribute("sm", "Info deleted successfully");
        } catch (Exception ex) {
            Logger.getLogger(SubCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/admin/list_sub_category";
    }
    
}
