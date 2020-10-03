package com.coderslab.restcontroller;

import com.coderslab.dao.QuestionDao;
import com.coderslab.entity.Question;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/questions")
public class QuestionRestController {

    @Autowired
    private QuestionDao questionDao;

    @RequestMapping(value = "/questionsByLessonId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Question> getAllQuestionsByLessonId(@PathVariable("id") int id) {
        return questionDao.getAllQuestionByLessonId(id);
    }

}
