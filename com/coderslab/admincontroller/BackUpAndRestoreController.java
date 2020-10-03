package com.coderslab.admincontroller;

import java.io.IOException;
import static java.lang.Math.log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/")
public class BackUpAndRestoreController {

    @RequestMapping(value = "/backup_data", method = RequestMethod.GET)
    public String loadBackUpPage(HttpServletRequest request, Model model) {
        
        
        model.addAttribute("sm", request.getParameter("sm"));
        model.addAttribute("em", request.getParameter("em"));
        return "/admin/backup_data";
    }

    @RequestMapping(value = "/doBackUp", method = RequestMethod.GET)
    public String doBackUp(HttpServletRequest request, Model model) {

        String dbpath = request.getSession().getServletContext().getRealPath("/");
        String webcut = dbpath.substring(0, dbpath.lastIndexOf("\\"));
        String buildcut = webcut.substring(0, webcut.lastIndexOf("\\"));
        String mainURLPath = buildcut.substring(0, buildcut.lastIndexOf("\\"));

        String dumpExePath = mainURLPath + "\\src\\main\\webapp\\resources\\exe\\mysqldump.exe";
        String host = "localhost";
        String port = "3306";
        String user = "root";
        String password = "1234";
        String database = "bes";
        String backupPath = mainURLPath + "\\src\\main\\webapp\\resources\\backup";

        boolean status = backupDataWithDatabase(dumpExePath, host, port, user, password, database, backupPath);

        if (status) {
            model.addAttribute("sm", "backup successfull");
            System.out.println("===== back up successfull");
        }

//        String path;
//        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//
//        String userName = "root";
//        String password = "1234";
//        String schemaName = "bes";
//
//        String dbpath = request.getSession().getServletContext().getRealPath("/");
//        String webcut = dbpath.substring(0, dbpath.lastIndexOf("\\"));
//        String buildcut = webcut.substring(0, webcut.lastIndexOf("\\"));
//        String mainURLPath = buildcut.substring(0, buildcut.lastIndexOf("\\"));
//        path = mainURLPath + "\\src\\main\\webapp\\resources\\backup\\" + date + ".sql";
//        String exe_file = mainURLPath + "\\src\\main\\webapp\\resources\\exe\\mysqldump.exe";
//
//        try {
//            Process p = null;
//            Runtime runtime = Runtime.getRuntime();
//            p = runtime.exec(exe_file + " -u" + userName + " -p" + password + " --add-drop-database -B " + schemaName + " -r " + path);
//            int processComplete = p.waitFor();
//            if (processComplete == 0) {
//                System.out.println("=========" + "backup successfull");
//                model.addAttribute("sm", "Backup Successfull");
//            } else {
//                System.out.println("==== could not create backup");
//                model.addAttribute("em", "Backup Not Successfull");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "redirect:/admin/backup_data";
    }

    @RequestMapping(value = "/doReStore", method = RequestMethod.POST)
    public String doReStore() {

        return "redirect:/admin/backup_data";
    }

    public boolean backupDataWithDatabase(String dumpExePath, String host, String port, String user, String password, String database, String backupPath) {
        boolean status = false;

        try {
            Process p = null;

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String filepath = "backup(with_DB)-" + database + "-" + host + "-(" + dateFormat.format(date) + ").sql";

            String batchCommand = "";
            if (password != "") {
                //Backup with database
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --password=" + password + " --add-drop-database -B " + database + " -r \"" + backupPath + "" + filepath + "\"";
            } else {
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --add-drop-database -B " + database + " -r \"" + backupPath + "" + filepath + "\"";
            }

            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec(batchCommand);
            int processComplete = p.waitFor();

            if (processComplete == 0) {
                status = true;
                System.out.println("Backup created successfully for with DB " + database + " in " + host + ":" + port);
            } else {
                status = false;
                System.out.println("Could not create the backup for with DB " + database + " in " + host + ":" + port);
            }

        } catch (IOException ioe) {
            System.out.println(ioe.getCause());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        return status;
    }

}
