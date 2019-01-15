package tasks.lab9;

import tasks.lab9.controller.Controller;
import tasks.lab9.view.Frame;

import javax.swing.*;

public class Main {
    //GlobalProject, task 20

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Frame frame = new Frame();
                Controller cntr = new Controller();
                cntr.setFrame(frame);
            }
        });

    }

}
