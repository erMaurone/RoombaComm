package com.hackingroomba.roombacomm.web;

import com.hackingroomba.roombacomm.RoombaComm;
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
 * Date: 01/01/2015
 * Time: 18:03
 */


public class DirectionServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(DirectionServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Received direction request");

        ServletContext ctx = request.getServletContext();
        StringBuilder output = (StringBuilder) ctx.getAttribute("screen");
        RoombaComm roombacomm = (RoombaComm) ctx.getAttribute("roomba");

        String turnLeft = request.getParameter("turnLeft");
        String forward  = request.getParameter("forward");
        String turnRight = request.getParameter("turnRight");
        String spinLeft = request.getParameter("spinLeft");
        String stop = request.getParameter("stop");
        String spinRight = request.getParameter("spinRight");
        String backward = request.getParameter("backward");
        String speed = request.getParameter("speedSlider");

        initializeRoomba(roombacomm);

        output.append("DIRECTION COMMAND; fwd=" + forward + ", stop=" + stop);
        logger.info("DIRECTION COMMAND; forward = " + forward + ", stop = " + stop);

        if (turnLeft != null) {
            output.append(turnLeft + '\n');
            roombacomm.turnLeft();
        }
        if (turnRight != null) {
            output.append(turnRight + '\n');
            roombacomm.turnRight();
        }
        if (forward != null) {
            output.append(forward + '\n');
            roombacomm.goForward();
        }
        if (spinLeft != null) {
            output.append(spinLeft + '\n');
            roombacomm.spinLeft();
        }
        if (spinRight != null) {
            output.append(spinRight + '\n');
            roombacomm.spinRight();
        }
        if (backward != null) {
            output.append(backward + '\n');
            roombacomm.goBackward();
        }
        if (stop != null) {
            output.append(stop + '\n');
            speed = "0";
        }

        roombacomm.pause(1000);
        roombacomm.stop();
        output.append(", speed=" + speed + '\n');

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(output.toString());

    }

    private void initializeRoomba(RoombaComm roombacomm) {
        roombacomm.startup();
        roombacomm.control();
        logger.info("Checking for Roomba... ");
        if( roombacomm.updateSensors() )
            logger.info("Roomba found!");
        else {
            logger.info("No Roomba. :(  Is it turned on?");
            //TODO try to connect?
        }
    }

}
