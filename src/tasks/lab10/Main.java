package tasks.lab10;

import tasks.lab10.controller.Controller;
import tasks.lab10.view.Frame;

public class Main {
    public static void main(String[] args){
        Frame fr = new Frame();
        Controller cntr = new Controller();
        cntr.setFrame(fr);
    }
}
