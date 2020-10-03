package com.coderslab.admincontroller;

import com.coderslab.dao.CourseDao;
import com.coderslab.dao.LessonDao;
import com.coderslab.entity.Course;
import com.coderslab.entity.Lesson;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class LessonController {

    @Autowired
    private LessonDao lessonDao;
    @Autowired
    private CourseDao courseDao;

    @RequestMapping(value = "/add_lesson", method = RequestMethod.GET)
    public String loadLessonPage(Model model, HttpServletRequest request) {

        model.addAttribute("lessons", lessonDao.getAllLessonWithCourse());
        model.addAttribute("courses", courseDao.getAllData(Course.class));
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));

        return "/admin/add_lesson";
    }

    @RequestMapping(value = "/list_lesson", method = RequestMethod.GET)
    public String loadListLessonPage(Model model, HttpServletRequest request) {

//        model.addAttribute("lessons", lessonDao.getAllData(Lesson.class));
        model.addAttribute("lessons", lessonDao.getAllLessonWithCourse());
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));

        return "/admin/list_lesson";
    }

    @RequestMapping(value = "/saveLesson", method = RequestMethod.POST)
    public String saveLesson(Lesson lesson, Model model, Course course) {
        lesson.setCourse(course);
        try {
            boolean status = lessonDao.saveData(lesson);
            model.addAttribute("sm", "Lesson Info saved successfully");
        } catch (Exception ex) {
            model.addAttribute("em", "Lesson Info not saved");
            ex.printStackTrace();
        }

        return "redirect:/admin/add_lesson";
    }

    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/uploadPPT", method = RequestMethod.POST)
    public String uploadLessonPPTFile(@RequestParam("lessonId") int lessonId, @RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String name = timeStamp + ".pptx";

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
                File dir = new File(rootPath + "\\src\\main\\webapp\\resources\\upload\\lesson_ppt");
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

                //return "You successfully uploaded file=" + name;
            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            System.out.println("You failed to upload " + name + " because the file was empty.");
        }

        //upload  info on database
        Lesson lesson = new Lesson();
        lesson.setLessonId(lessonId);
        lesson.setPresentationFile("upload/lesson_ppt/" + name);

        try {
            boolean status = lessonDao.saveLessonPPTFile(lesson);
            if (status) {
                model.addAttribute("sm", "Lesson PPT file upload successfull");
            } else {
                model.addAttribute("em", "file not upload");
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/add_lesson";
    }

    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/uploadNotes", method = RequestMethod.POST)
    public String uploadLessonNotesFile(@RequestParam("lessonId") int lessonId, @RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String name = timeStamp + ".pdf";

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
                File dir = new File(rootPath + "\\src\\main\\webapp\\resources\\upload\\lesson_pdf");
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

                //return "You successfully uploaded file=" + name;
            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            System.out.println("You failed to upload " + name + " because the file was empty.");
        }

        //upload  info on database
        Lesson lesson = new Lesson();
        lesson.setLessonId(lessonId);
        lesson.setNotesFile("upload/lesson_pdf/" + name);

        try {
            boolean status = lessonDao.saveLessonNotesFile(lesson);
            if (status) {
                model.addAttribute("sm", "Lesson Notes file upload successfull");
            } else {
                model.addAttribute("em", "file not upload");
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/add_lesson";
    }

    @RequestMapping(value = "/update_lesson_status/{id}/{status}", method = RequestMethod.GET)
    public String updateLessonStatus(Model model, @PathVariable("id") int id, @PathVariable("status") boolean status) {

        boolean l_status = !status;

        Lesson l = new Lesson();
        l.setStatus(l_status);
        l.setLessonId(id);

        try {
            boolean stat = lessonDao.updateLessonStatus(l);
            if (stat) {
                model.addAttribute("sm", "Status Update successfull");
            } else {
                model.addAttribute("em", "Status Not Update");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/admin/list_lesson";
    }

    @RequestMapping(value = "/edit_lesson/{id}", method = RequestMethod.GET)
    public String editLessonPage(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        model.addAttribute("courses", courseDao.getAllData(Course.class));

        model.addAttribute("lesson", lessonDao.findLessonWithCourseByLessonId(id));

        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_lesson";

    }

    @RequestMapping(value = "/updateLesson", method = RequestMethod.POST)
    public String updateLesson(Model model, Lesson lesson, Course course) {
        lesson.setCourse(course);
        
        System.out.println("===========" + lesson.getCourse().getCourseId());
        System.out.println("===========" + lesson.getLessonTitle());
        System.out.println("===========" + lesson.getDescription());
        
        try {
            boolean status = lessonDao.updateLesson(lesson);
            if (status) {
                model.addAttribute("sm", "Lesson Info update successfully");
            } else {
                model.addAttribute("em", "Lesson Info not update");
            }
        } catch (Exception ex) {
            Logger.getLogger(LessonController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/edit_lesson/" + lesson.getLessonId();
    }

    @RequestMapping(value = "/delete_lesson/{id}", method = RequestMethod.GET)
    public String deleteLesson(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        Lesson l = new Lesson();
        l.setLessonId(id);

        try {
            boolean status = lessonDao.deleteData(l);
            if (status) {
                model.addAttribute("sm", "Lesson Info deleted successfully");
            } else {
                model.addAttribute("em", "Lesson info not deleted");
            }
        } catch (Exception ex) {
            Logger.getLogger(LessonController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/list_lesson";
    }

    @RequestMapping(value = "/edit_lesson_ppt/{id}", method = RequestMethod.GET)
    public String editLessonPPT(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("lesson", lessonDao.getLessonByLessonId(id));
        } catch (Exception ex) {
            Logger.getLogger(LessonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_lesson_ppt";
    }

    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/updateLessonPPT", method = RequestMethod.POST)
    public String updateLessonPPTFile(@RequestParam("lessonId") int lessonId, @RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String name = timeStamp + ".pptx";

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
                File dir = new File(rootPath + "\\src\\main\\webapp\\resources\\upload\\lesson_ppt");
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

                //return "You successfully uploaded file=" + name;
            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            System.out.println("You failed to upload " + name + " because the file was empty.");
        }

        //upload  info on database
        Lesson lesson = new Lesson();
        lesson.setLessonId(lessonId);
        lesson.setPresentationFile("upload/lesson_ppt/" + name);

        try {
            boolean status = lessonDao.saveLessonPPTFile(lesson);
            if (status) {
                model.addAttribute("sm", "Lesson PPT file Update successfull");
            } else {
                model.addAttribute("em", "file not update");
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/edit_lesson_ppt/" + lesson.getLessonId();
    }

    @RequestMapping(value = "/edit_lesson_notes/{id}", method = RequestMethod.GET)
    public String editLessonNotes(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("lesson", lessonDao.getLessonByLessonId(id));
        } catch (Exception ex) {
            Logger.getLogger(LessonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_lesson_notes";
    }

    @RequestMapping(value = "/updateLessonNotes", method = RequestMethod.POST)
    public String updateLessonNotes(@RequestParam("lessonId") int lessonId, @RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String name = timeStamp + ".pdf";

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
                File dir = new File(rootPath + "\\src\\main\\webapp\\resources\\upload\\lesson_pdf");
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

                //return "You successfully uploaded file=" + name;
            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            System.out.println("You failed to upload " + name + " because the file was empty.");
        }

        //upload  info on database
        Lesson lesson = new Lesson();
        lesson.setLessonId(lessonId);
        lesson.setNotesFile("upload/lesson_pdf/" + name);

        try {
            boolean status = lessonDao.saveLessonNotesFile(lesson);
            if (status) {
                model.addAttribute("sm", "Lesson Notes file update successfull");
            } else {
                model.addAttribute("em", "file not update");
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/edit_lesson_notes/" + lesson.getLessonId();
    }

}
