
package com.coderslab.restcontroller;

import com.coderslab.dao.EventDao;
import com.coderslab.entity.Event;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/events")
public class EventRestController {
    
    @Autowired
    private EventDao eventDao;
    
    @RequestMapping(value = "/allEvents", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Event> getAllEvent(){
        return eventDao.getAllData(Event.class);
    }
    
    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Event getSelectedEventByEventId(@PathVariable("id") int id){
        try {
            return eventDao.findData(Event.class, id);
        } catch (Exception ex) {
            Logger.getLogger(EventRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
