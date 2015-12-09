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
 * User: mauro
 * Date: 02/01/2015
 * Time: 16:12
 */

public class PanTiltServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(PanTiltServlet.class);
    private static final String SERVO_BLASTER ="/dev/servoblaster";
    private static final String SERVO_0="0=";
    private static final String SERVO_1="1=";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("in post");
        ServletContext ctx = request.getServletContext();
        StringBuilder output = (StringBuilder) ctx.getAttribute("screen");
        String previousPan = (String) ctx.getAttribute("previousPan");
        String previousTilt = (String) ctx.getAttribute("previousTilt");
        String pan = request.getParameter("panSlider");
        String tilt = request.getParameter("tiltSlider");
        String panTilt = request.getParameter("panTiltSubmit");
        logger.debug("got the parameters: " +
                "previousPan=" + previousPan +
                "previousTilt=" + previousTilt +
                "pan=" + pan +
                "tilt=" +tilt);

        FileWriter servos = new FileWriter(SERVO_BLASTER);

        if (panTilt == null || (previousPan.equals(pan) && previousTilt.equals(tilt)) )
            return;
        logger.info("some value changed");
        output.append(panTilt);
        if (!previousPan.equals(pan)) {
            logger.info("changing pan to " + pan + "%");
            servos.write(SERVO_0 + pan + "%" + '\n');
            servos.flush();
            output.append(", set pan = " + pan + "%");
        }

        if (!previousTilt.equals(tilt)) {
            logger.info("changing tilt to " + tilt + "%");
            servos.write(SERVO_1 + tilt + "%" + '\n');
            servos.flush();
            output.append(", set tilt = " + tilt + "%" );
        }
        servos.close();
        output.append('\n');

        ctx.setAttribute("previousPan", pan);
        ctx.setAttribute("previousTilt", tilt);

        request.setAttribute("output", output);
        request.setAttribute("pan", pan);
        request.setAttribute("tilt", tilt);
        RequestDispatcher rd = request.getRequestDispatcher("/comm");
        rd.forward(request, response);
    }
}
