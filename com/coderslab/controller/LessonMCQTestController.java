
package com.coderslab.controller;

import com.coderslab.dao.LessonDao;
import com.coderslab.dao.LessonExamResultDao;
import com.coderslab.dao.QuestionDao;
import com.coderslab.entity.Lesson;
import com.coderslab.entity.LessonExamResult;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LessonMCQTestController {
    
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private LessonExamResultDao lessonExamResultDao;
    @Autowired
    private LessonDao lessonDao;
    
    
    @RequestMapping(value = "/lessonMcqTest/{id}", method = RequestMethod.GET)
    public String loadLessonMcqTest(Model model, @PathVariable("id") int id){
        
        model.addAttribute("lessonId", id);
//        model.addAttribute("questions", questionDao.getAllQuestionByLessonId(id));
        model.addAttribute("page", "lessonMcqTest_page");
        return "/index";
    }
    
    
    @RequestMapping(value = "/saveLessonExamResult", method = RequestMethod.POST)
    public String saveLessonExamResult(LessonExamResult lessonExamResult, Model model){
        
        try {
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Lesson l = lessonDao.getLessonByLessonId(lessonExamResult.getLessonId());
            lessonExamResult.setUsername(u.getUsername());
            lessonExamResult.setLessonName(l.getLessonTitle());
            lessonExamResult.setExamDate(new Date());
            
            lessonExamResultDao.saveData(lessonExamResult);
            model.addAttribute("username", u.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        System.out.println("====" + lessonExamResult.toString());
        
        
        model.addAttribute("page", "submitResultSuccess_page");
        return "/index";
    }
    
    
    @RequestMapping(value = "/lessonExamResultChart", method = RequestMethod.GET)
    public String lessonExamResultChart(Model model){
        try {
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("results", lessonExamResultDao.getAllLessonExamResultByUsername(u.getUsername()));
            model.addAttribute("username", u.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("page", "lesson_result_page");
        return "/index";
    }
    
}
