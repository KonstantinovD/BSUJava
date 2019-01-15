package tasks.test3;

import tasks.test3.controller.Controller;
import tasks.test3.view.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                View frame = new View();

                Controller controller = new Controller();
                controller.setView(frame);
            }
        });
    }
}
