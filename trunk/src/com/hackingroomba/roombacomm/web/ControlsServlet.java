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
 * Date: 02/01/2015
 * Time: 16:12
 */

public class ControlsServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(ControlsServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        ServletContext ctx = request.getServletContext();
        StringBuilder output = (StringBuilder) ctx.getAttribute("screen");
        RoombaComm roomba = (RoombaComm) ctx.getAttribute("roomba");
        if(roomba.isConnected()) output.append("ROOMBA STATUS = connected" + '\n');
        else output.append("ROOMBA STATUS = not connected" + '\n');

        String powerOn = request.getParameter("powerOn");
        String powerOff = request.getParameter("powerOff");

        String vacuumOn = request.getParameter("vacuumOn");
        String vacuumOff = request.getParameter("vacuumOff");

        String sensorData = request.getParameter("sensorData");
        String chargeData = request.getParameter("chargeData");

        String wakeUp = request.getParameter("wakeUp");
        String reset = request.getParameter("reset");
        String passive = request.getParameter("passive");
        String full = request.getParameter("full");
        String safe = request.getParameter("safe");
        String max = request.getParameter("max");
        String clean = request.getParameter("clean");
        String spot = request.getParameter("spot");
        String dock = request.getParameter("dock");


        //StringBuilder output = new StringBuilder("CONTROLS COMMAND, received instruction ");
        if (powerOn != null) output.append(powerOn + '\n');
        if (powerOff != null) output.append(powerOff + '\n');
        if (vacuumOn != null) output.append(vacuumOn + '\n');
        if (vacuumOff != null) output.append(vacuumOff + '\n');
        if (wakeUp != null) output.append(wakeUp + '\n');
        if (reset != null) output.append(reset + '\n');
        if (passive != null) output.append(passive + '\n');
        if (full != null) output.append(full + '\n');
        if (safe != null) output.append(safe + '\n');
        if (max != null) output.append(max + '\n');
        if (clean != null) output.append(clean + '\n');
        if (spot != null) output.append(spot + '\n');
        if (dock != null) output.append(dock + '\n');

        if (sensorData != null) {
            output.append(sensorData + '\n');
            //TODO retrieve sensor data and append
        }

        if (chargeData != null) {
            output.append(chargeData + '\n');
            //TODO retrieve chargeData and append
        }

        request.setAttribute("output", output);
        RequestDispatcher rd = request.getRequestDispatcher("/comm");
        rd.forward(request, response);
    }
}
