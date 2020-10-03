package com.coderslab.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author codersLab
 */
public class FileUploadHelper {

    //upload setting
    private static final int MEMOTY_THESHOLD = 1024 * 1024 * 5; //5MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; //40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; //50MB

    public Map<String, String> doUpload(String uploadDirectory, int scaledWidth, int scaledHeight, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //location to store file uploaded
        Map<String, String> returnedInfo = new HashMap<String, String>();

        PrintWriter out = response.getWriter();
        if (!ServletFileUpload.isMultipartContent(request)) {
            out.println("Error: form must have enctype=multipart/form-data");
            out.flush();
        }

        //Configure Upload Settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMOTY_THESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String dbpath = request.getSession().getServletContext().getRealPath("/");
        String webcut = dbpath.substring(0, dbpath.lastIndexOf("\\"));
        String buildcut = webcut.substring(0, webcut.lastIndexOf("\\"));
        String mainURLPath = buildcut.substring(0, buildcut.lastIndexOf("\\"));
        String uploadPath = mainURLPath + uploadDirectory;

        //create a directory if doesnot exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    returnedInfo.put(item.getFieldName(), item.getString());
                } else if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                    fileName = timeStamp + ".jpg";

                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);

                    //saves the file on projects folder
                    item.write(storeFile);

                    //resize image
                    if (scaledWidth > 0 && scaledHeight > 0) {
                        String inputImagePath = filePath;
                        String outputImagepath = filePath;
                        ImageResizer.resize(inputImagePath, outputImagepath, scaledWidth, scaledHeight);
                    }

                    returnedInfo.put("fileName", fileName);
                    returnedInfo.put("message", "success");
                    

                }
            }
            return returnedInfo;
        } catch (Exception e) {
            returnedInfo.put("message", "error");
            returnedInfo.put("stacTrace", e.getMessage());
        }

        return null;
    }

}
