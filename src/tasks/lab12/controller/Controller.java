package tasks.lab12.controller;

import tasks.lab12.view.Frame;

import javax.swing.*;

public class Controller {

    private Frame mView;

    public void setFrame(Frame frame){
        mView = frame;
        initializeListeners();
        setInitialAppState();
    }

    private void initializeListeners() {
        mView.getUpperButton().addActionListener(e -> {
            int indices[] =  mView.getLeftList().getSelectedIndices();

            DefaultListModel<String> modelFrom = mView.getLeftListModel();
            DefaultListModel<String> modelTo = mView.getRightListModel();
            for(int i=indices.length - 1; i >=0; i--){
                modelTo.addElement(modelFrom.remove(indices[i]));
            }
        });

        mView.getLowerButton().addActionListener(e -> {
            int indices[] =  mView.getRightList().getSelectedIndices();

            DefaultListModel<String> modelFrom = mView.getRightListModel();
            DefaultListModel<String> modelTo = mView.getLeftListModel();
            for(int i=indices.length - 1; i >=0; i--){
                modelTo.addElement(modelFrom.remove(indices[i]));
            }
        });
    }

    private void setInitialAppState(){

    }
}
