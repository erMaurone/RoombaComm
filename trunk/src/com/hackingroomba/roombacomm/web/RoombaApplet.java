package com.hackingroomba.roombacomm.web;

import com.hackingroomba.roombacomm.RoombaCommFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: mauro
 * Date: 23/12/2014
 * Time: 16:48
 */
public class RoombaApplet extends JApplet  {

    //Called when this applet is loaded into the browser.
    public void init() {
        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    RoombaCommFrame rcPanel = new RoombaCommFrame(true);
                    rcPanel.setResizable(true);
                    rcPanel.pack();
                    rcPanel.setVisible(true);
                    add(rcPanel);
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }

    /**
     * Create the GUI. For thread safety, this method should
     * be invoked from the event-dispatching thread.
     */
    private void createGUI()
    {
        JLabel appletLabel = new JLabel( "I'm a Swing Applet" );
        appletLabel.setHorizontalAlignment( JLabel.CENTER );
        appletLabel.setFont(new Font("Serif", Font.PLAIN, 36));
        add( appletLabel );
        setSize(200,200);
    }

    public static void main(String[] args)
    {
        JApplet applet = new RoombaApplet();
        applet.init();
        RoombaCommFrame rcPanel = new RoombaCommFrame(true);
        rcPanel.setResizable(true);
        rcPanel.pack();
        rcPanel.setVisible(true);
        rcPanel.add(applet);

        applet.start();
    }
}
