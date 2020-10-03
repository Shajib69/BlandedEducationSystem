
package com.coderslab.restcontroller;

import com.coderslab.dao.CourseDao;
import com.coderslab.entity.Course;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/courses")
public class CourseRestController {
    
    @Autowired
    private CourseDao courseDao;
    
    @RequestMapping(value = "/all_courses", method = RequestMethod.GET)
    public List<Course> getCourses(){
        return courseDao.getAllData(Course.class);
    }
    
    @RequestMapping(value = "/all_courses_with_lesson_and_topics", method = RequestMethod.GET)
    public List<Course> getAllCourseName(){
        return courseDao.getAllCourseWithLessonAndTopics();
    }
    
    @RequestMapping(value = "/course_with_details_by_cid/{id}", method = RequestMethod.GET)
    public List<Course> getCourseWithDetailsByCourseId(@PathVariable("id") int id){
        return courseDao.getCourseWithDetailsByCourseId(id);
    }
    
    @RequestMapping(value = "/all_courses_with_lesson", method = RequestMethod.GET)
    public List<Course> getAllCourseWithLesson(){
        return courseDao.getAllCourseWithLesson();
    }
    
    
}
