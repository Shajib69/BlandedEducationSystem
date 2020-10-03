
package com.coderslab.restcontroller;

import com.coderslab.dao.StudentDao;
import com.coderslab.entity.Student;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/students")
public class StudentRestController {
    
    @Autowired
    private StudentDao studentDao;
    
    @RequestMapping(value = "/allStudent", method = RequestMethod.GET)
    public List<Student> getAllStudent(){
        return studentDao.getAllData(Student.class);
    }
    
    @RequestMapping(value = "/student/{username}", method = RequestMethod.GET)
    public Student getStudentByUsername(@PathVariable("username") String username){
        try {
            return studentDao.getStudentByUserName(username);
        } catch (Exception ex) {
            Logger.getLogger(StudentRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
