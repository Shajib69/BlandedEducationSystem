
package com.coderslab.restcontroller;

import com.coderslab.dao.LessonDao;
import com.coderslab.entity.Lesson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lessons")
public class LessonRestController {
    
    @Autowired
    private LessonDao lessonDao;
    
    @RequestMapping(value = "/all_lessons", method = RequestMethod.GET)
    public List<Lesson> allLessons(){
        return lessonDao.getAllData(Lesson.class);
    }
    
}
