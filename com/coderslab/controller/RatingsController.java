
package com.coderslab.controller;

import com.coderslab.dao.RatingsDao;
import com.coderslab.entity.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RatingsController {
    @Autowired
    private RatingsDao ratingsDao;
    
    
    
}
