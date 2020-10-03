
package com.coderslab.restcontroller;

import com.coderslab.dao.CourseDao;
import com.coderslab.dao.LessonDao;
import com.coderslab.dao.TopicsDao;
import com.coderslab.entity.Course;
import com.coderslab.entity.Lesson;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/topics")
public class TopicsRestController {
    
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private LessonDao lessonDao;
    @Autowired
    private TopicsDao topicsDao;
    
    @RequestMapping(value = "/allCoursesInfo", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Course> getAllCoursesName(){
        return courseDao.getAllCourseInfo();
    }
    
    
    @RequestMapping(value = "/allCourses", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Course> getAllCourse(){
//        return courseDao.getAllData(Course.class);
//        return courseDao.getAllCourse();
        return courseDao.getAllCourseWithLessonAndTopics();
    }
    
    @RequestMapping(value = "/allCourseName", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Course> getAllCourseName(){
        return courseDao.getAllCourseName();
    }
    
    @RequestMapping(value = "/allLessons", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Lesson> getAllLesson(){
        return lessonDao.getAllData(Lesson.class);
    }
    
    @RequestMapping(value = "/lessonByCourseId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Lesson> getLessonByCourseId(@PathVariable("id") int id){
        try {
            return lessonDao.getLessonByCourseIdByJDBC(id);
        } catch (Exception ex) {
            Logger.getLogger(TopicsRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
