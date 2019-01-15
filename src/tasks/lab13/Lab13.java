package tasks.lab13;

import tasks.lab13.controller.Controller;
import tasks.lab13.model.Model;
import tasks.lab13.view.Frame;

import javax.swing.*;

public class Lab13 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Frame frame = new Frame();
                Model model = new Model();
                model.setView(frame);

                Controller controller = new Controller();
                controller.setModel(model);
                controller.setFrame(frame);
            }
        });
    }
}
