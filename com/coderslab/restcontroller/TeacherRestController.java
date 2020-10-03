
package com.coderslab.restcontroller;

import com.coderslab.dao.TeacherDao;
import com.coderslab.entity.Teacher;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teachers")
public class TeacherRestController {
    
    @Autowired
    private TeacherDao teacherDao;
    
    @RequestMapping(value = "/allTeacher", method = RequestMethod.GET)
    public List<Teacher> getAllTeacher(){
        return teacherDao.getAllData(Teacher.class);
    }
    
    @RequestMapping(value = "/teacher/{username}", method = RequestMethod.GET)
    public Teacher getTeacherByUsername(@PathVariable("username") String username){
        try {
            return teacherDao.getTeacherByUsername(username);
        } catch (Exception ex) {
            Logger.getLogger(TeacherRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
