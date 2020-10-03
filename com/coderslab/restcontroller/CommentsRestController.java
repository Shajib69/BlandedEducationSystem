
package com.coderslab.restcontroller;

import com.coderslab.dao.CommentsDao;
import com.coderslab.entity.Comments;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/comments")
public class CommentsRestController {

    @Autowired
    private CommentsDao commentsDao;
    
    @RequestMapping(value = "/saveComments", method = RequestMethod.POST)
    public Comments saveComment(@RequestBody Comments comments){
        
        System.out.println("===" + comments.toString());
        
        try {
            commentsDao.saveData(comments);
        } catch (Exception ex) {
            Logger.getLogger(CommentsRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return comments;
    }
    
    @RequestMapping(value = "/allCommentsByTopicsId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Comments> getAllComments(@PathVariable("id") int id){
        return commentsDao.getAllCommentsByTopicsId(id);
    }
    
//    @RequestMapping(value = "/allCommentsDetailsByTopicsId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
//    public List<Comments> getAllCommentsDetails(@PathVariable("id") int id){
//        return commentsDao.getAllCommentsDetailsByTopicsId(id);
//    }
    
    
    
    
}
