package tasks.lab10.controller;

import javafx.scene.input.KeyCode;
import tasks.lab10.view.Frame;

import java.awt.*;
import java.awt.event.*;

public class Controller {

    private Frame mView;
    public Controller(){
        mInitialMouseDragPos = MouseInfo.getPointerInfo().getLocation();
    }

    public void setFrame(Frame frame){
        mView = frame;
        initializeListeners();
        setInitialAppState();
    }


    private Point mInitialMouseDragPos;
    private boolean mButtonMouseFocus;
    private void initializeListeners() {
        //panel events
        MouseAdapter panelMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                mView.getStatusLabel().setText("");
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                mView.setButtonPosition(new Dimension(e.getX(), e.getY()));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mView.getStatusLabel().setText(e.getX() + "," + e.getY());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mView.getStatusLabel().setText(e.getX() + "," + e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mView.getStatusLabel().setText(e.getX() + "," + e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mView.getStatusLabel().setText(e.getX() + "," + e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mView.getStatusLabel().setText(e.getX() + "," + e.getY());
            }
        };
        MouseMotionAdapter panelMouseMotionAdapter = new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mView.getStatusLabel().setText(e.getX() + "," + e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mView.getStatusLabel().setText(e.getX() + "," + e.getY());
            }

        };
        mView.getRootPanel().addMouseMotionListener(panelMouseMotionAdapter);
        mView.getRootPanel().addMouseListener(panelMouseAdapter);



        mView.getButton().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                processKeyEvents(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                processKeyEvents(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        /*//button events
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                processKeyEvents(ke);
                return false;//give opportunity for another dispatcher to process current key event
            }
        });
        */
        MouseAdapter buttonMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mButtonMouseFocus = true;
                super.mouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mView.getStatusLabel().setText("");
                //fireEvent(new MouseEvent(MouseEvent.MOUSE_RELEASED));
                //mView fireEvent(new MouseEvent(MouseEvent.MOUSE_RELEASED));

                mButtonMouseFocus = false;
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                mView.setButtonPosition(new Dimension(e.getX() + mView.getButton().getX(),
                        e.getY() + mView.getButton().getY()));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mView.setButtonPosition(new Dimension(e.getX() + mView.getButton().getX(),
                        e.getY() + mView.getButton().getY()));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mView.setButtonPosition(new Dimension(e.getX() + mView.getButton().getX(),
                        e.getY() + mView.getButton().getY()));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mView.setButtonPosition(new Dimension(e.getX() + mView.getButton().getX(),
                        e.getY() + mView.getButton().getY()));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mView.setButtonPosition(new Dimension(e.getX() + mView.getButton().getX(),
                        e.getY() + mView.getButton().getY()));
            }
        };
        MouseMotionAdapter buttonMouseMotionAdapter = new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mView.getStatusLabel().setText((e.getX() + mView.getButton().getX())
                        + "," + (e.getY() + mView.getButton().getY()));
                //for drag&drop
                mInitialMouseDragPos.x = e.getX();
                mInitialMouseDragPos.y = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if(e.isControlDown()) {
                    mView.getStatusLabel().setText((e.getX() + mView.getButton().getX())
                            + "," + (e.getY() + mView.getButton().getY()));
                    mView.setButtonPosition(new Dimension(e.getX() - mInitialMouseDragPos.x + mView.getButton().getX(),
                            e.getY() - mInitialMouseDragPos.y + mView.getButton().getY()));
                }else {
                    mView.getStatusLabel().setText((e.getX() + mView.getButton().getX())
                            + "," + (e.getY() + mView.getButton().getY()));
                }
            }
        };
        mView.getButton().addKeyListener(new KeyAdapter() {

        });
        mView.getButton().addMouseMotionListener(buttonMouseMotionAdapter);
        mView.getButton().addMouseListener(buttonMouseAdapter);
    }

    private boolean mIsCtrlPressed;
    private String mButtonName = "";
    private void processKeyEvents(KeyEvent ke){
        mIsCtrlPressed = ke.isControlDown(); //set ctrl key state
        //process input characters from keyboard when button is in focus
        if(mButtonMouseFocus && ke.getID() == KeyEvent.KEY_PRESSED){
            if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                if(!mButtonName.isEmpty()){
                    mButtonName = mButtonName.substring(0, mButtonName.length()-1);
                }
            }else{
                if(Character.isDefined(ke.getKeyChar())) {//is char exist
                    mButtonName += ke.getKeyChar();
                }
            }
            mView.getButton().setText(mButtonName);
        }
    }

    private void setInitialAppState(){

    }


}
