
package com.coderslab.admincontroller;

import com.coderslab.dao.TeacherDao;
import com.coderslab.dao.UsersDao;
import com.coderslab.entity.Teacher;
import com.coderslab.entity.Users;
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
@RequestMapping("/admin")
public class TeacherController {
    
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private UsersDao usersDao;
    
    
    @RequestMapping(value = "/list_teacher", method = RequestMethod.GET)
    public String loadMangeStudent(Model model, Teacher teacher, HttpServletRequest request){
        model.addAttribute("users", usersDao.getAllTeacherUsers());
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/list_teacher";
    }
    
    @RequestMapping(value = "/update_teacher_status/{id}/{status}", method = RequestMethod.GET)
    public String updateStudentStatus(Model model, @PathVariable("id") int id, @PathVariable("status") boolean status){
        
        try {
            Users u = usersDao.findData(Users.class, id);
            u.setEnabled(!status);
            usersDao.updateData(u);
            model.addAttribute("sm", "Status update successfull");
        } catch (Exception ex) {
            model.addAttribute("em", "Status not update");
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/admin/list_teacher";
    }
    
    @RequestMapping(value = "/delete_teacher/{id}", method = RequestMethod.GET)
    public String deleteStudent(Model model, @PathVariable("id") int id){
        
        try {
            Users u = usersDao.findData(Users.class, id);
            teacherDao.deleteTeacherByUsername(u.getUsername());
            usersDao.deleteData(u);
            model.addAttribute("sm", "Teacher delete successfully");
        } catch (Exception ex) {
            model.addAttribute("em", "not delete");
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/admin/list_teacher";
    }
    
    
}
