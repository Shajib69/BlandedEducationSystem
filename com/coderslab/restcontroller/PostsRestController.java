
package com.coderslab.restcontroller;

import com.coderslab.dao.PostsDao;
import com.coderslab.entity.Posts;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts")
public class PostsRestController {
    
    @Autowired
    private PostsDao postsDao;
    
    @RequestMapping(value = "/allPosts", method = RequestMethod.GET)
    public ArrayList<Posts> getAllPosts(){
        return (ArrayList<Posts>) postsDao.getAllData(Posts.class);
    }
    
}
