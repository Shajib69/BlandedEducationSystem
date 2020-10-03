
package com.coderslab.controller;

import com.coderslab.dao.EventDao;
import com.coderslab.entity.Event;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LiveClassEventController {
    
    @Autowired
    private EventDao eventDao;
    
    @RequestMapping("/live_class")
    public String loadLiveClassPage(Model model) {
        
        model.addAttribute("page", "live_class_page");
        return "index";
    }
    
    @RequestMapping("/live_class_event/{id}")
    public String loadLiveClassEventPage(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("event", eventDao.findData(Event.class, id));
        } catch (Exception ex) {
            Logger.getLogger(LiveClassEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("page", "live_class_event_page");
        return "index";
    }
    
}
