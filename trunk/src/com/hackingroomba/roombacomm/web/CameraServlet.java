package com.hackingroomba.roombacomm.web;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: mauro
 * commands to mjpeg_stream via fifo pipe - see
 * https://raw.githubusercontent.com/silvanmelchior/userland/master/host_applications/linux/apps/raspicam/README_RaspiMJPEG.md
 * Date: 02/01/2015
 * Time: 16:12
 */

public class CameraServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(CameraServlet.class);
    private static final String MJPEG_STREAM_FIFO_PIPE ="/var/www/piCam/FIFO";
    private static final String MJPEG_STREAM_ON_CMD="ru 1";
    private static final String MJPEG_STREAM_OFF_CMD="ru 0";
    private static final String ON_CMD = "on";
    private static final String OFF_CMD = "off";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("***** received camera post request");
        ServletContext ctx = request.getServletContext();
        StringBuilder output = (StringBuilder) ctx.getAttribute("screen");
        String camera = request.getParameter("camera");

        if (camera !=null ) {
            output.append("camera " + camera + '\n');
            logger.info("***** received camera command: " + camera + " - writing to file");
            FileWriter pipeWriter = new FileWriter(MJPEG_STREAM_FIFO_PIPE);

            if (camera.equals(ON_CMD))
                pipeWriter.write(MJPEG_STREAM_ON_CMD);
            else if (camera.equals(OFF_CMD))
                pipeWriter.write(MJPEG_STREAM_OFF_CMD);

            pipeWriter.flush();
            pipeWriter.close();
            logger.info("***** written " + camera + " to file " + MJPEG_STREAM_FIFO_PIPE);
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(output.toString());
        logger.info("***** exiting - job done");
    }

}
