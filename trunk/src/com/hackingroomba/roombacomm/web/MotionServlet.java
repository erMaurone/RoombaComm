package com.hackingroomba.roombacomm.web;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * see motion commands in
 * https://raw.githubusercontent.com/silvanmelchior/userland/master/host_applications/linux/apps/raspicam/README_RaspiMJPEG.md
 * User: mauro
 * Date: 02/01/2015
 * Time: 16:12
 */

public class MotionServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(MotionServlet.class);
    private static final String MJPEG_STREAM_FIFO_PIPE ="/var/www/piCam/FIFO";
    private static final String MOTION_ON="md 1";
    private static final String MOTION_OFF="md 0";
    private static final String MJPEG_STREAM_OFF_CMD="ru 0";
    private static final String ON_CMD = "on";
    private static final String OFF_CMD = "off";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("***** received motion post request");
        ServletContext ctx = request.getServletContext();
        StringBuilder output = (StringBuilder) ctx.getAttribute("screen");
        String motion = request.getParameter("motion");


        if (motion != null) {
            output.append("motion " + motion + '\n');
            logger.info("***** received motion command: " + motion + " - writing to file");
            try {
                FileWriter pipeWriter = new FileWriter(MJPEG_STREAM_FIFO_PIPE);

                if (motion.equals(ON_CMD)) {
                    pipeWriter.write(MOTION_ON);
                    pipeWriter.flush();
                } else if (motion.equals(OFF_CMD)) {
                    pipeWriter.write(MOTION_OFF);
                    pipeWriter.flush();
                    Thread.sleep(3000);
                    pipeWriter.write(MJPEG_STREAM_OFF_CMD);
                    pipeWriter.flush();
                }

                pipeWriter.close();
            } catch (IOException ioe) {
                logger.error("***** error while writing to FIFO");
                ioe.printStackTrace();
            } catch (InterruptedException ie) {
                logger.error("***** error while sleeping");
                ie.printStackTrace();
            }
            logger.info("***** written " + motion + " to file " + MJPEG_STREAM_FIFO_PIPE);
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(output.toString());
        logger.info("***** exiting - job done");
    }
}
