package com.coderslab.admincontroller;

import com.coderslab.dao.PostsDao;
import com.coderslab.dao.SubCategoryDao;
import com.coderslab.entity.Posts;
import com.coderslab.entity.SubCategory;
import com.coderslab.util.ImageResizer;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/admin")
public class PostsController {

    @Autowired
    private PostsDao postsDao;
    @Autowired
    private SubCategoryDao subCategoryDao;

    @RequestMapping(value = "/add_post", method = RequestMethod.GET)
    public String loadAddPost(Model model, HttpServletRequest request) {
        model.addAttribute("subCategories", subCategoryDao.getAllData(SubCategory.class));
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/add_post";
    }

    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    public String savePost(Model model, Posts posts, HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        posts.setPostShorten(30);
        posts.setAuthorName(user.getUsername());
        posts.setPostDate(new Date());

        String[] subCategoryId = request.getParameterValues("subCategoryId");

        if (subCategoryId.length <= 0) {
            model.addAttribute("em", "Please select Atleast one SubCategory");
            return "redirect:/admin/add_post";
        }

        System.out.println(subCategoryId.length);
        String sci = subCategoryId[0];
        for (int i = 1; i < subCategoryId.length; i++) {
            sci = sci + ",";
            sci = sci + subCategoryId[i];
        }

        posts.setSubCategoryId(sci);

        try {
            boolean status = postsDao.saveData(posts);
            model.addAttribute("sm", "Post Created Successfully");
        } catch (Exception ex) {
            Logger.getLogger(PostsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/add_post";
    }

    @RequestMapping(value = "/list_posts", method = RequestMethod.GET)
    public String loadListPosts(Model model, HttpServletRequest request) {
        model.addAttribute("posts", postsDao.getAllData(Posts.class));
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/list_posts";
    }

    @RequestMapping(value = "/uploadPostsPhoto/{postId}", method = RequestMethod.GET)
    public String uploadPostsPhoto(Model model, @PathVariable("postId") int postId, HttpServletRequest request) {
        try {
            model.addAttribute("post", postsDao.findData(Posts.class, postId));
        } catch (Exception ex) {
            Logger.getLogger(PostsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_post_photo";
    }

    @RequestMapping(value = "/updatePostPhoto", method = RequestMethod.POST)
    public String updatePostPhoto(HttpServletRequest request, @RequestParam("file") MultipartFile file, Model model, @RequestParam("postId") int postId) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String name = timeStamp + ".png";

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // constructs the directory path to store upload file // this path is relative to application's directory
                String dbpath = request.getSession().getServletContext().getRealPath("/");
                String webcut = dbpath.substring(0, dbpath.lastIndexOf("\\"));
                String buildcut = webcut.substring(0, webcut.lastIndexOf("\\"));
                String mainURLPath = buildcut.substring(0, buildcut.lastIndexOf("\\"));

                // Creating the directory to store file
                //thir
                String rootPath = mainURLPath;
                File dir = new File(rootPath + "\\src\\main\\webapp\\resources\\upload\\post_photo");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                System.out.println("======== Server File Location="
                        + serverFile.getAbsolutePath());

                ImageResizer.resize(dir + "\\" + name, dir + "\\" + name, 300, 174);

                //return "You successfully uploaded file=" + name;
            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            System.out.println("You failed to upload " + name + " because the file was empty.");
        }

        Posts post = new Posts();
        post.setPostId(postId);
        post.setImage("upload/post_photo/" + name);

        try {
            Posts p = postsDao.findData(Posts.class, post.getPostId());
            p.setImage(post.getImage());
            postsDao.updateData(p);
            model.addAttribute("sm", "Photo Update Successfully");
        } catch (Exception ex) {
            model.addAttribute("em", "Photo not update");
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/uploadPostsPhoto/" + postId;
    }
    
    @RequestMapping(value = "/update_post_status/{postId}/{status}", method = RequestMethod.GET)
    public String updatePostStatus(Model model, @PathVariable("postId") int postId, @PathVariable("status") boolean status){
        try {
            Posts p = postsDao.findData(Posts.class, postId);
            p.setStatus(!status);
            boolean stat = postsDao.updateData(p);
            if(stat){
                model.addAttribute("sm", "Status Update successfull");
            }else{
                model.addAttribute("em", "Status not update");
            }
        } catch (Exception ex) {
            Logger.getLogger(PostsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/admin/list_posts";
    }
    
    @RequestMapping(value = "/edit_post/{postId}", method = RequestMethod.GET)
    public String editPosts(Model model, @PathVariable("postId") int postId, HttpServletRequest request){
        try {
            model.addAttribute("post", postsDao.findData(Posts.class, postId));
            model.addAttribute("subCategories", subCategoryDao.getAllData(SubCategory.class));
        } catch (Exception ex) {
            Logger.getLogger(PostsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_post";
    }
    
    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    public String updatePost(Model model, Posts posts){
        try {
            Posts p = postsDao.findData(Posts.class, posts.getPostId());
            p.setPostTitle(posts.getPostTitle());
            p.setPostSubTitle(posts.getPostSubTitle());
            p.setPostContent(posts.getPostContent());
            boolean status = postsDao.updateData(p);
            if(status){
                model.addAttribute("sm", "Post info update successfull");
            }
        } catch (Exception ex) {
            Logger.getLogger(PostsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/admin/edit_post/" + posts.getPostId();
    }
    
    @RequestMapping(value = "/delete_post/{postId}", method = RequestMethod.GET)
    public String deletePost(Model model, @PathVariable("postId") int postId){
        try {
            boolean status = postsDao.deleteData(postsDao.findData(Posts.class, postId));
            model.addAttribute("sm", "Item deleted successfuly");
        } catch (Exception ex) {
            Logger.getLogger(PostsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/admin/list_posts";
    }

}
