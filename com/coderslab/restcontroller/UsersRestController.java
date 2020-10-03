
package com.coderslab.restcontroller;

import com.coderslab.dao.UsersDao;
import com.coderslab.entity.Users;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UsersRestController {
    
    @Autowired
    private UsersDao usersDao;
    
    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public List<Users> allUsers(){
        return usersDao.getAllData(Users.class);
    }
    
    @RequestMapping(value = "/userByUserName/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Users getUserByUserName(@PathVariable("username") String username){
        return usersDao.getUserByUserName(username);
    }
    
}
