package com.coderslab.controller;

import com.coderslab.dao.PostsDao;
import com.coderslab.dao.RatingsDao;
import com.coderslab.entity.Posts;
import com.coderslab.entity.Ratings;
import com.coderslab.restcontroller.RatingsrestController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogController {

    @Autowired
    private PostsDao postsDao;
    @Autowired
    private RatingsDao ratingsDao;

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String loadBlogPage(Model model) {
        model.addAttribute("page", "blog_page");
        return "index";
    }

    @RequestMapping(value = "/single_blog/{postId}", method = RequestMethod.GET)
    public String loadSingleBlogPage(Model model, @PathVariable("postId") int postId) {
        try {
            Posts p = postsDao.findData(Posts.class, postId);
            p.setViews(p.getViews() + 1);
            postsDao.updateData(p);
            model.addAttribute("post", postsDao.findData(Posts.class, postId));
        } catch (Exception ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        try {
//            //ratings find
//            int ratingNumber = 0;
//            List<Ratings> ratingList = ratingsDao.findRatingsByBlogPostId(postId);
//            if (ratingList.size() > 0) {
//                for (Ratings rating : ratingList) {
//                    ratingNumber = ratingNumber + rating.getRating();
//                }
//                ratingNumber = ratingNumber / ratingList.size();
//            } 
//            model.addAttribute("rating", ratingNumber);
//            System.out.println("=== " + ratingNumber);
//        } catch (Exception e) {
//        }

        try {
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("username", u.getUsername());
            System.out.println(u.getUsername());
        } catch (Exception e) {
            System.out.println("no user name");
        }
        model.addAttribute("page", "single_blog_page");
        return "index";
    }

    @RequestMapping(value = "/savePostRatings", method = RequestMethod.POST)
    public String savePostRatings(Ratings r) {
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        r.setUsername(u.getUsername());
        try {
            ratingsDao.saveData(r);
        } catch (Exception ex) {
            Logger.getLogger(RatingsrestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/single_blog/" + r.getPostId();
    }

}
