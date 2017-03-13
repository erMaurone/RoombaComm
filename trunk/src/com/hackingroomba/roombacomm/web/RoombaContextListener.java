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
    private static final String DEFAULT_PAN = "50";
    private static final String DEFAULT_TILT = "50";
    private static final String SERVO_BLASTER ="/dev/servoblaster";
    private static final String SERVO_0="0=";
    private static final String SERVO_1="1=";
    private static final String ROOMBA_PORT = "/dev/ttyUSB0";
    private static final String MJPEG_STREAM_FIFO_PIPE ="/var/www/piCam/FIFO";
    private static final String MJPEG_STREAM_OFF_CMD="ru 0";
    // motion feature is switched off - see comments in roombacomm.jsp
    //private static final String MOTION_OFF_CMD="md 0";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //TODO - MV - read /dev/servoblaster to get current servos angle and update the sliders accordingly
        logger.info("###### Initializing application");
        ServletContext ctx = servletContextEvent.getServletContext();
        RoombaComm roomba = new RoombaCommSerial();
        ctx.setAttribute(ROOMBA,roomba);
        ctx.setAttribute("previousPan", DEFAULT_PAN);
        ctx.setAttribute("previousTilt", DEFAULT_TILT);
        StringBuilder screen = new StringBuilder();
        resetServos();
        //resetVideo();
        ctx.setAttribute(SCREEN, screen);
        connectRoomba(roomba);
        logger.info("###### Initialization completed.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        RoombaComm roomba = (RoombaCommSerial) ctx.getAttribute(ROOMBA);
        logger.info("Disconnecting roomba");
        roomba.disconnect();
        resetServos();
        //resetVideo();

    }

    private void resetVideo() {
        logger.info("###### Setting camera to off");
        try {
            FileWriter pipeWriter = new FileWriter(MJPEG_STREAM_FIFO_PIPE);
            /*
            pipeWriter.write(MOTION_OFF_CMD);
            pipeWriter.flush();
            Thread.sleep(3000);*/
            pipeWriter.write(MJPEG_STREAM_OFF_CMD);
            pipeWriter.flush();
            pipeWriter.close();
            logger.info("###### Set camera off");
        } catch (IOException ioe) {
            logger.error("###### exception while setting camera to off");
            ioe.printStackTrace();
        } /*catch (InterruptedException ie) {
            ie.printStackTrace();
        }*/
    }

    private void resetServos() {
        logger.info("###### Setting servos positions to" + DEFAULT_PAN + "%");
        try {
            FileWriter servos = new FileWriter(SERVO_BLASTER);
            servos.write(SERVO_0 + DEFAULT_PAN + "%" + '\n');
            servos.flush();
            servos.write(SERVO_1 + DEFAULT_TILT + "%" + '\n');
            servos.flush();
            logger.info("###### Set servos positions");
        } catch (IOException ioe) {
            logger.error("###### exception while setting servos position");
            ioe.printStackTrace();
        }
    }

    private void connectRoomba(RoombaComm roomba) {
        logger.info("###### Connecting to roomba");
        if( ! roomba.connect( ROOMBA_PORT ) ) {
            logger.info("###### Couldn't connect to "+ ROOMBA_PORT);
        }
        logger.info("###### Roomba startup on port "+ ROOMBA_PORT);
        sing(roomba);

    }

    private void sing(RoombaComm roombacomm) {
        logger.info("###### Playing some notes");
        roombacomm.playNote( 72, 10 );  // C
        roombacomm.pause( 200 );
        roombacomm.playNote( 79, 10 );  // G
        roombacomm.pause( 200 );
        roombacomm.playNote( 76, 10 );  // E
        roombacomm.pause( 200 );
    }

    private void spinRun(RoombaComm roombacomm) {


        roombacomm.startup();
        roombacomm.control();
        roombacomm.pause(30);

        logger.info("C###### hecking for Roomba... ");
        if( roombacomm.updateSensors() )
            logger.info("###### Roomba found!");
        else
            logger.info("###### No Roomba. :(  Is it turned on?");

        //com.hackingroomba.roombacomm.updateSensors();

        logger.info("###### Playing some notes");
        roombacomm.playNote( 72, 10 );  // C
        roombacomm.pause( 200 );
        roombacomm.playNote( 79, 10 );  // G
        roombacomm.pause( 200 );
        roombacomm.playNote( 76, 10 );  // E
        roombacomm.pause( 200 );

        logger.info("###### Spinning left, then right");
        roombacomm.spinLeft();
        roombacomm.pause(1000);
        roombacomm.spinRight();
        roombacomm.pause(1000);
        roombacomm.stop();

        logger.info("###### Going forward, then backward");
        roombacomm.goForward();
        roombacomm.pause(1000);
        roombacomm.goBackward();
        roombacomm.pause(1000);
        roombacomm.stop();


        logger.info("###### Moving via send()");
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

        logger.info("###### Done");

    }
}
