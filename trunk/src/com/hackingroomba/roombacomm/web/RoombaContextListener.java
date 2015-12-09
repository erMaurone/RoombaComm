package com.hackingroomba.roombacomm.web;

import com.hackingroomba.roombacomm.RoombaComm;
import com.hackingroomba.roombacomm.RoombaCommSerial;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: mauro
 * Date: 04/01/2015
 * Time: 18:56
 */

public class RoombaContextListener implements ServletContextListener {
    //TODO lots of duplicate values - consider moving to web.xml properties
    private final Logger logger = Logger.getLogger(RoombaContextListener.class);
    private static final String ROOMBA= new String("roomba");
    private static final String SCREEN= new String("screen");
    private static final String defaultPan = "50";
    private static final String defaultTilt = "50";
    private static final String STOP_VIDEO_COMMAND="/home/pi/roombaProj/bin/camera_off.sh";
    private static final String SERVO_BLASTER ="/dev/servoblaster";
    private static final String SERVO_0="0=";
    private static final String SERVO_1="1=";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        RoombaComm roomba = new RoombaCommSerial();
        ctx.setAttribute(ROOMBA,roomba);
        ctx.setAttribute("previousPan", defaultPan);
        ctx.setAttribute("previousTilt", defaultTilt);
        StringBuilder screen = new StringBuilder();
        resetServos();
        ctx.setAttribute(SCREEN, screen);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        RoombaComm roomba = (RoombaCommSerial)ctx.getAttribute(ROOMBA);
        roomba.disconnect();
        resetServos();

        Runtime r = Runtime.getRuntime();
        try {
            Process process = r.exec(STOP_VIDEO_COMMAND);
            logger.info("exit value on stop video call" + process.exitValue());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void resetServos() {
        try {
            FileWriter servos = new FileWriter(SERVO_BLASTER);
            servos.write(SERVO_0 + defaultPan + "%" + '\n');
            servos.write(SERVO_1 + defaultTilt + "%" + '\n');
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
