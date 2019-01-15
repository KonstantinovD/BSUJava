package tasks.lab11;

import tasks.lab11.controller.Controller;
import tasks.lab11.view.Frame;

public class Main {
    public static void main(String[] args){
        Frame fr = new Frame();
        Controller cntr = new Controller();
        cntr.setFrame(fr);
    }
}