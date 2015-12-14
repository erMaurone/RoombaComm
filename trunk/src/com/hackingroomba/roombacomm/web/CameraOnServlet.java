package com.hackingroomba.roombacomm.web;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: mauro
 * Date: 02/01/2015
 * Time: 16:12
 */

public class CameraOnServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(CameraOnServlet.class);
    private static final String START_VIDEO_COMMAND="/home/pi/roombaProj/bin/camera_on.sh";
    private static final String STOP_VIDEO_COMMAND="/home/pi/roombaProj/bin/camera_off.sh";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext ctx = request.getServletContext();
        StringBuilder output = (StringBuilder) ctx.getAttribute("screen");
        String cameraOn = request.getParameter("cameraOn");


        if (cameraOn != null) {
            output.append(cameraOn + '\n');
            startCapture();
        }

        request.setAttribute("output", output);
        RequestDispatcher rd = request.getRequestDispatcher("/comm");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("received request from " + request.getRequestURI());

        ServletContext ctx = request.getServletContext();
        StringBuilder output = (StringBuilder) ctx.getAttribute("screen");
        String cameraOn = request.getParameter("cameraOn");

        if (cameraOn != null) {
            output.append(cameraOn + '\n');
            startCapture();
        }

        logger.info(output.toString());
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(output.toString());
    }

    private void startCapture() {
        logger.info("starting video capture");
        executeCommand(START_VIDEO_COMMAND);
    }

    private void executeCommand(String cmd) {
        Runtime r = Runtime.getRuntime();
        try {
            Process process = r.exec(cmd);
            logger.info("exit value of command " + cmd + " = " + process.waitFor());
            int len;
            if ((len = process.getErrorStream().available()) > 0) {
                byte[] buf = new byte[len];
                process.getErrorStream().read(buf);
                logger.info("Command error: " + new String(buf) + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
