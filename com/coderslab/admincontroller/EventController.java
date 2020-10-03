package com.coderslab.admincontroller;

import com.coderslab.dao.EventDao;
import com.coderslab.entity.Event;
import com.coderslab.util.ImageResizer;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/admin")
public class EventController {

    @Autowired
    private EventDao eventDao;

    @InitBinder
    public void myInitBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        binder.registerCustomEditor(Date.class, "eventDate", new CustomDateEditor(format, false));
    }

    @RequestMapping(value = "/add_event", method = RequestMethod.GET)
    public String loadAddEvent(Model model, HttpServletRequest request) {
        model.addAttribute("event", new Event());
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/add_event";
    }

    @RequestMapping(value = "/list_event", method = RequestMethod.GET)
    public String loadManageEvent(Model model, HttpServletRequest request) {
        model.addAttribute("events", eventDao.getAllData(Event.class));
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/list_event";
    }

    @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
    public String saveEvent(Model model, @Valid Event event, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println("========" + error.getDefaultMessage());

            }
            model.addAttribute("em", "Enter Correct Date format");
            return "redirect:/admin/add_event";
        }

        //customize the video url
        String s = request.getParameter("videoUrl");
        int startIndex = s.indexOf("https://");
        int endIndex = s.indexOf("frameborder");
        String cut = s.substring(startIndex, endIndex - 2);

        //get the username 
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        event.setVideoUrl(cut);
        event.setUsername(u.getUsername());

        System.out.println("=====" + event);

        try {
            eventDao.saveData(event);
            model.addAttribute("sm", "Event Create Successfully");
        } catch (Exception ex) {
            model.addAttribute("em", "Event Not Create");
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/add_event";
    }

    @RequestMapping(value = "/edit_event_notes/{id}", method = RequestMethod.GET)
    public String updateEventNotes(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("event", eventDao.findData(Event.class, id));
        } catch (Exception ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_event_notes";
    }

    @RequestMapping(value = "/updateEventNotes", method = RequestMethod.POST)
    public String uploadEventNotesFile(@RequestParam("eventId") int eventId, @RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {

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
                File dir = new File(rootPath + "\\src\\main\\webapp\\resources\\upload\\event_pdf");
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
        Event event = new Event();
        event.setEventId(eventId);
        event.setNotesFile("upload/event_pdf/" + name);

        try {
            boolean status = eventDao.saveEventNotesFile(event);
            if (status) {
                model.addAttribute("sm", "Event Notes file upload successfull");
            } else {
                model.addAttribute("em", "file not upload");
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/edit_event_notes/" + eventId;
    }

    @RequestMapping(value = "/edit_event_ppt/{id}", method = RequestMethod.GET)
    public String updateEventPPT(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("event", eventDao.findData(Event.class, id));
        } catch (Exception ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_event_ppt";
    }

    @RequestMapping(value = "/updateEventPPT", method = RequestMethod.POST)
    public String updateLessonPPTFile(@RequestParam("eventId") int eventId, @RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {

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
                File dir = new File(rootPath + "\\src\\main\\webapp\\resources\\upload\\event_ppt");
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
        Event event = new Event();
        event.setEventId(eventId);
        event.setPresentationFile("upload/event_ppt/" + name);

        try {
            boolean status = eventDao.saveEventPPTFile(event);
            if (status) {
                model.addAttribute("sm", "Event PPT file Update successfull");
            } else {
                model.addAttribute("em", "file not update");
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/edit_event_ppt/" + eventId;
    }

    @RequestMapping(value = "/edit_event_video/{id}", method = RequestMethod.GET)
    public String updateEventVideo(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("event", eventDao.findData(Event.class, id));
        } catch (Exception ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_event_video";
    }

    @RequestMapping(value = "/updateEventVideoUrl", method = RequestMethod.POST)
    public String updateEventVideoUrl(Model model, Event event, HttpServletRequest request) {

        //customize the video url
        String s = request.getParameter("videoUrl");
        int startIndex = s.indexOf("https://");
        int endIndex = s.indexOf("frameborder");
        String cut = s.substring(startIndex, endIndex - 2);

        event.setVideoUrl(cut);

        try {
            eventDao.updateEventVideo(event);
            model.addAttribute("sm", "Video Update Successfully");
        } catch (Exception ex) {
            model.addAttribute("em", "Video not update");
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/edit_event_video/" + event.getEventId();
    }

    @RequestMapping(value = "/update_event_status/{id}/{status}", method = RequestMethod.GET)
    public String updateEventStatus(@PathVariable("id") int id, @PathVariable("status") boolean status, Model model) {
        Event event = new Event();
        event.setEventId(id);
        event.setStatus(!status);

        try {
            eventDao.updateEventStatus(event);
            model.addAttribute("sm", "Status update successfully");
        } catch (Exception ex) {
            model.addAttribute("em", "Status not update");
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/list_event";
    }

    @RequestMapping(value = "/edit_event/{id}", method = RequestMethod.GET)
    public String editEvent(@PathVariable("id") int id, Model model, HttpServletRequest request) {

        try {
            model.addAttribute("event", eventDao.findData(Event.class, id));
        } catch (Exception ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_event";
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
    public String updateEvent(Event event, Model model) {

        try {
            Event e = eventDao.findData(Event.class, event.getEventId());
            e.setEventName(event.getEventName());
            e.setEventCreatorName(event.getEventCreatorName());
            e.setEventDate(event.getEventDate());
            e.setDescription(event.getDescription());

            eventDao.updateData(e);
            model.addAttribute("sm", "event update Successfull");
        } catch (Exception ex) {
            model.addAttribute("em", "event update failed");
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/edit_event/" + event.getEventId();

    }

    @RequestMapping(value = "/delete_event/{id}", method = RequestMethod.GET)
    public String deleteEvent(@PathVariable("id") int id, Model model) {

        Event event = new Event();
        event.setEventId(id);

        try {
            eventDao.deleteData(event);
            model.addAttribute("sm", "event delete Successfull");
        } catch (Exception ex) {
            model.addAttribute("em", "event not delete");
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/list_event";
    }

    @RequestMapping(value = "/editEventPhoto/{id}", method = RequestMethod.GET)
    public String editEventPhoto(@PathVariable("id") int id, Model model, HttpServletRequest request) {

        try {
            model.addAttribute("event", eventDao.findData(Event.class, id));
        } catch (Exception ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/edit_event_photo";
    }

    @RequestMapping(value = "/updateEventPhoto", method = RequestMethod.POST)
    public String updateEventPhoto(HttpServletRequest request, @RequestParam("file") MultipartFile file, Model model, @RequestParam("eventId") int eventId) {
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
                File dir = new File(rootPath + "\\src\\main\\webapp\\resources\\upload\\event_photo");
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

        Event event = new Event();
        event.setEventPhoto("upload/event_photo/" + name);
        event.setEventId(eventId);

        try {
            Event e = eventDao.findData(Event.class, event.getEventId());
            e.setEventPhoto(event.getEventPhoto());
            eventDao.updateData(e);
            model.addAttribute("sm", "Photo Update Successfully");
        } catch (Exception ex) {
            model.addAttribute("em", "Photo not update");
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/admin/editEventPhoto/" + eventId;
    }

}
