package ru.job4j.dream.servlets;

import ru.job4j.dream.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String name = req.getParameter("id");
        File downloadFile = null;
        System.out.println(name);
        for (File file : new File(Config.getConfig().getProperty("path")).listFiles()) {
            System.out.println(file.toString());
            String[] arStr = file.getName().split("\\.");
            if (name.equals(arStr[0])) {
                downloadFile = file;
                break;
            }
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile  + "\"");
        try (FileInputStream stream = new FileInputStream(downloadFile)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}
