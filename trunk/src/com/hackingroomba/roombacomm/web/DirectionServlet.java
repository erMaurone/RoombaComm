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

        ServletContext ctx = request.getServletContext();
        StringBuilder output = (StringBuilder) ctx.getAttribute("screen");
        RoombaComm roomba = (RoombaComm) ctx.getAttribute("roomba");
        if(roomba.isConnected()) output.append("ROOMBA STATUS = connected" + '\n');
        else output.append("ROOMBA STATUS = not connected"+ '\n');

        output.append("ROOMBA ports = " + roomba.listPorts());

        String turnLeft = request.getParameter("turnLeft");
        String forward  = request.getParameter("forward");
        String turnRight = request.getParameter("turnRight");
        String spinLeft = request.getParameter("spinLeft");
        String stop = request.getParameter("stop");
        String spinRight = request.getParameter("spinRight");
        String backward = request.getParameter("backward");
        String speed = request.getParameter("speedSlider");

        output.append("DIRECTION COMMAND, received instruction ");
        if (turnLeft != null) output.append(turnLeft + '\n');
        if (turnRight != null) output.append(turnRight + '\n');
        if (forward != null) output.append(forward + '\n');
        if (spinLeft != null) output.append(spinLeft + '\n');
        if (spinRight != null) output.append(spinRight + '\n');
        if (backward != null) output.append(backward + '\n');
        if (stop != null) {
            output.append(stop + '\n');
            speed = "0";
        }
        output.append(", speed " + speed + '\n');

        request.setAttribute("output", output);
        request.setAttribute("speed", speed);
        RequestDispatcher rd = request.getRequestDispatcher("/comm");
        rd.forward(request, response);
    }
}
