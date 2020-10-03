package com.coderslab.restcontroller;

import com.coderslab.dao.RatingsDao;
import com.coderslab.entity.Ratings;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingsrestController {

    @Autowired
    private RatingsDao ratingsDao;

    @RequestMapping(value = "/savePostRatings", method = RequestMethod.POST, headers = "Accept=application/json")
    public Ratings savePostRatings(@RequestBody Ratings r) {
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        r.setUsername(u.getUsername());
        try {
            ratingsDao.saveData(r);
        } catch (Exception ex) {
            Logger.getLogger(RatingsrestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    @RequestMapping(value = "/getAllRatingsByPostsId/{pid}", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<Ratings> getAllRatingsByPostsId(@PathVariable("pid") int pid){
        try {
            return ratingsDao.findRatingsByBlogPostId(pid);
        } catch (Exception ex) {
            Logger.getLogger(RatingsrestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
