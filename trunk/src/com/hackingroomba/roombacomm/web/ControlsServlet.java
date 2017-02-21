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
        RoombaComm roombacomm = (RoombaComm) ctx.getAttribute("roomba");
        initializeRoomba(roombacomm);

        String vacuumOn = request.getParameter("vacuumOn");
        String vacuumOff = request.getParameter("vacuumOff");
        String sensorData = request.getParameter("sensorData");
        String chargeData = request.getParameter("chargeData");
        String reset = request.getParameter("reset");
        String max = request.getParameter("max");
        String clean = request.getParameter("clean");
        String spot = request.getParameter("spot");
        String dock = request.getParameter("dock");
        String sing = request.getParameter("sing");
        StringBuffer command =  new StringBuffer("CONTROLS COMMAND, received instruction ");

        if (vacuumOn != null) {
            command.append(vacuumOn);
            roombacomm.vacuum(true);
        }
        if (vacuumOff != null) {
            command .append(vacuumOff);
            roombacomm.vacuum(false);
        }
        if (reset != null) {
            command.append(reset);
            roombacomm.reset();
        }
        if (max != null) {
            command.append(max);
            roombacomm.max();
        }
        if (clean != null) {
            command.append(clean);
            roombacomm.clean();
        }
        if (spot != null) {
            command.append(spot);
            roombacomm.spot();
        }
        if (dock != null) {
            command.append(dock);
            roombacomm.dock();
        }
        if (sensorData != null) {
            command.append(sensorData);
            String sensorsValues = roombacomm.sensorsAsString();
            output.append(sensorsValues + '\n');
            logger.info("Received sensors data = " + sensorData);
        }
        if (chargeData != null) {
            command.append(chargeData);
            String chargeValue = roombacomm.chargeDataAsString();
            output.append(chargeValue + '\n');
            output.append("*** Charge Range: 0 – 65535 mAh" + '\n');
            logger.info("Received charge data = " + chargeValue);
        }
        if(sing != null){
            command.append(sing);
            sing(roombacomm);
        }

        output.append(command.toString() + '\n');

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(output.toString());
    }


    private void initializeRoomba(RoombaComm roombacomm) {
        roombacomm.startup();
        roombacomm.control();
        logger.info("Checking for Roomba... ");
        if (roombacomm.updateSensors())  {
            logger.info("Roomba found!");
            sing(roombacomm);
        } else {
            logger.info("No Roomba. :(  Is it turned on?");
            //TODO try to connect?
        }
    }

    private void sing(RoombaComm roombacomm) {
        logger.info("Playing some notes");
        roombacomm.playNote( 72, 10 );  // C
        roombacomm.pause( 200 );
        roombacomm.playNote( 79, 10 );  // G
        roombacomm.pause( 200 );
        roombacomm.playNote( 76, 10 );  // E
        roombacomm.pause( 200 );
    }


    private void spinRun(RoombaComm roombacomm) {

        logger.info("Roomba startup on port ");
        roombacomm.startup();
        roombacomm.control();
        roombacomm.pause(30);

        logger.info("Checking for Roomba... ");
        if( roombacomm.updateSensors() )
            logger.info("Roomba found!");
        else
            logger.info("No Roomba. :(  Is it turned on?");

        roombacomm.updateSensors();

        sing(roombacomm);

        logger.info("Spinning left, then right");
        roombacomm.spinLeft();
        roombacomm.pause(1000);
        roombacomm.spinRight();
        roombacomm.pause(1000);
        roombacomm.stop();

        logger.info("Going forward, then backward");
        roombacomm.goForward();
        roombacomm.pause(1000);
        roombacomm.goBackward();
        roombacomm.pause(1000);
        roombacomm.stop();


        logger.info("Moving via send()");
        byte cmd[] = {(byte)RoombaComm.DRIVE,
                (byte)0x00,(byte)0xfa, (byte)0x00,(byte)0x00};
        roombacomm.send( cmd ) ;
        roombacomm.pause(1000);
        roombacomm.stop();
        cmd[1] = (byte)0xff;
        cmd[2] = (byte)0x05;
        roombacomm.send( cmd ) ;
        roombacomm.pause(1000);
        roombacomm.stop();

        logger.info("Done");

    }

}
