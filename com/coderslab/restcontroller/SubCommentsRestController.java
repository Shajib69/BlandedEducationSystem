
package com.coderslab.restcontroller;

import com.coderslab.dao.SubCommentsDao;
import com.coderslab.entity.Subcomments;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subComments")
public class SubCommentsRestController {
    
    @Autowired
    private SubCommentsDao subCommentsDao;
    
    @RequestMapping(value = "/saveSubComments", method = RequestMethod.POST)
    public Subcomments saveComment(@RequestBody Subcomments subComments){
        
        System.out.println("===" + subComments.toString());
        
        try {
            subCommentsDao.saveData(subComments);
        } catch (Exception ex) {
            Logger.getLogger(SubCommentsRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return subComments;
    }
    
    @RequestMapping(value = "/allSubComments", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Subcomments> getAllSubComments(){
        return subCommentsDao.getAllData(Subcomments.class);
    }
    
    
}
