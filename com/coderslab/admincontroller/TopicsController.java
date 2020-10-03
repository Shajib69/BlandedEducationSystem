package com.coderslab.admincontroller;

import com.coderslab.dao.CourseDao;
import com.coderslab.dao.LessonDao;
import com.coderslab.dao.TopicsDao;
import com.coderslab.entity.Course;
import com.coderslab.entity.Lesson;
import com.coderslab.entity.Topics;
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
public class TopicsController {

    @Autowired
    private TopicsDao topicsDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private LessonDao lessonDao;

    @RequestMapping(value = "/add_topics", method = RequestMethod.GET)
    public String loadTopicPage(Model model, HttpServletRequest request) {
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/add_topics";
    }

    @RequestMapping(value = "/list_topics", method = RequestMethod.GET)
    public String loadManageTopics(Model model, HttpServletRequest request) {
        model.addAttribute("topics", topicsDao.getAllwithLesson());
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/list_topics";
    }

    @RequestMapping(value = "/saveTopics", method = RequestMethod.POST)
    public String saveTopics(Model model, Topics topics, Course course, Lesson lesson) {
        //        System.out.println("========" + topics.toString());
        
        topics.setLesson(lesson);
        try {
            boolean status = topicsDao.saveData(topics);
            if (status) {
                model.addAttribute("sm", "Topics created Successfully");
            } else {
                model.addAttribute("sm", "Topics not create");
            }
        } catch (Exception ex) {
            Logger.getLogger(TopicsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/add_topics";
    }

    @RequestMapping(value = "/edit_topic/{id}", method = RequestMethod.GET)
    public String editTopics(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("topic", topicsDao.getAllWithLessonByTpicsId(id));
            model.addAttribute("courses", courseDao.getAllCourseWithLessonAndTopics());
            model.addAttribute("lessons", lessonDao.getAllLessonName());
        } catch (Exception ex) {
            Logger.getLogger(TopicsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_topics";
    }

    @RequestMapping(value = "/updateTopics", method = RequestMethod.POST)
    public String updateTopics(Model model, Topics topics, Course course, Lesson lesson) {
        
        topics.setLesson(lesson);
        try {
            boolean status = topicsDao.updateTopics(topics);
            if (status) {
                model.addAttribute("sm", "Topics info update successfull");
            } else {
                model.addAttribute("em", "Topics info not update");
            }
        } catch (Exception ex) {
            Logger.getLogger(TopicsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/edit_topic/" + topics.getTopicsId();
    }

    @RequestMapping(value = "/update_topics_status/{id}/{status}", method = RequestMethod.GET)
    public String updateTopicsStatus(Model model, @PathVariable("id") int id, @PathVariable("status") boolean status) {

        boolean t_status = !status;

        Topics t = new Topics();
        t.setTopicsId(id);
        t.setStatus(t_status);

        try {
            boolean stat = topicsDao.updateTopicsStatus(t);
            if (stat) {
                model.addAttribute("sm", "Status Update successfull");
            } else {
                model.addAttribute("em", "Status Not Update");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/admin/list_topics";
    }

    @RequestMapping(value = "/delete_topic/{id}", method = RequestMethod.GET)
    public String deleteTopics(@PathVariable("id") int id, Model model) {
        try {
            topicsDao.deleteTopicsById(id);
            model.addAttribute("sm", "Topics Delete successfully");
        } catch (Exception ex) {
            model.addAttribute("em", "Topics Not Delete");
            ex.printStackTrace();
        }

        return "redirect:/admin/list_topics";
    }
    
    @RequestMapping(value = "/addTopicsVideo/{id}", method = RequestMethod.GET)
    public String addTopicsVideo(Model model, @PathVariable("id") int id, HttpServletRequest request){
        
        model.addAttribute("topic", topicsDao.getAllWithLessonByTpicsId(id));
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        
        return "/admin/add_topics_video";
    }
    
    @RequestMapping(value = "/updateTopicsVideoUrl", method = RequestMethod.POST)
    public String updateTopicsVideoUrl(Model model, HttpServletRequest request){
    
        String s = request.getParameter("videoUrl");
        int startIndex = s.indexOf("https://");
        int endIndex = s.indexOf("frameborder");
        String cut = s.substring(startIndex, endIndex-2);
        
        Topics topics = new Topics();
        topics.setTopicsId(Integer.parseInt(request.getParameter("topicsId")));
        topics.setTopicsTitle(request.getParameter("topicsTitle"));
        topics.setVideoUrl(cut);
        
        System.out.println("========" + cut);
        
        try {
            boolean status = topicsDao.updateTopicsVideoUrl(topics);
            if(status){
                model.addAttribute("sm", "Video Url set Successfull");
            }else{
                model.addAttribute("em", "Video Url not set");
            }
        } catch (Exception ex) {
            Logger.getLogger(TopicsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/admin/addTopicsVideo/" + topics.getTopicsId();
    }

}
