package tasks.lab12;

import tasks.lab12.controller.Controller;
import tasks.lab12.view.Frame;

import javax.swing.*;

public class Lab12 {
    //psvm
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Frame frame = new Frame();
                Controller cntr = new Controller();
                cntr.setFrame(frame);
            }
        });
    }

}
