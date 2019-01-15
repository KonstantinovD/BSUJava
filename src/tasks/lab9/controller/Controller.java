package tasks.lab9.controller;

import tasks.lab9.series.Exponential;
import tasks.lab9.series.Liner;
import tasks.lab9.series.Series;
import tasks.lab9.view.Frame;

import java.io.File;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Controller {

    private Series mSeries;

    private Frame mView;

    public Controller(){}

    public void setFrame(Frame frame){
        mView = frame;
        initializeListeners();
        setInitialAppState();
    }

    private final int INITIAL_NUMBER_OF_ELEMENTS = 10;
    private final float INITIAL_FIRST_MEMBER = 0;
    private final float INITIAL_STEP = 1;
    private void setInitialAppState(){
        //Set liner series whit some initial values
        mSeries = new Liner(INITIAL_NUMBER_OF_ELEMENTS,
                INITIAL_FIRST_MEMBER,
                INITIAL_STEP);
        mView.getFirstElemInput().setText(String.valueOf(INITIAL_FIRST_MEMBER));
        mView.getSeriesStepInput().setText(String.valueOf(INITIAL_STEP));
        mView.getNumberOfElementsInput().setText(String.valueOf(INITIAL_NUMBER_OF_ELEMENTS));
        updateElements();

        mIsLiner = true;
        mView.getLinerRadioBtn().setSelected(true);
        mView.setSeriesStepName("d");
    }

    private void initializeListeners(){
        //radio button group
        final String exponentRadioBtnId = "exp";
        final String linerRadioBtnId = "lin";
        mView.getLinerRadioBtn().setActionCommand(linerRadioBtnId);
        mView.getExponentRadioBtn().setActionCommand(exponentRadioBtnId);
        ActionListener al = e -> {
            switch(e.getActionCommand()) {
                case exponentRadioBtnId:{
                    setSeriesType(false);
                    break;
                }
                case linerRadioBtnId:{
                    setSeriesType(true);
                    break;
                }
            }
        };
        mView.getLinerRadioBtn().addActionListener(al);
        mView.getExponentRadioBtn().addActionListener(al);
        //init openfile button
        mView.getSaveToFileBtn().addActionListener(e -> {
            saveToFile();
        });
        mView.getCalculateSumBtn().addActionListener(e -> {
            showSum();
        });
        //initialize changing Series qualities
        mView.getFirstElemInput().getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {}
            public void removeUpdate(DocumentEvent e) {
                try {
                    String pattern = mView.getFirstElemInput().getText();
                    if(isValidLastSymbol(pattern)) {
                        setFirstElement(Float.parseFloat(pattern));
                    }else mView.getElementListModel().clear();
                } catch (Exception ex) {
                    mView.getElementListModel().clear();
                }
            }
            public void insertUpdate(DocumentEvent e) {
                try {
                    String pattern = mView.getFirstElemInput().getText();
                    if(isValidLastSymbol(pattern)) {
                        setFirstElement(Float.parseFloat(pattern));
                    }else mView.getElementListModel().clear();
                } catch (Exception ex) {
                    mView.getElementListModel().clear();
                }
            }
        });
        mView.getNumberOfElementsInput().getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {}
            public void removeUpdate(DocumentEvent e) {
                try {
                    setNumberOfElements(Integer.parseInt(mView.getNumberOfElementsInput().getText()));
                } catch (Exception ex) {
                    mView.getElementListModel().clear();
                }
            }
            public void insertUpdate(DocumentEvent e) {
                try {
                    setNumberOfElements(Integer.parseInt(mView.getNumberOfElementsInput().getText()));
                } catch (Exception ex) {
                    mView.getElementListModel().clear();
                }
            }
        });
        mView.getSeriesStepInput().getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {}
            public void removeUpdate(DocumentEvent e) {
                try {
                    String pattern = mView.getSeriesStepInput().getText();
                    if(isValidLastSymbol(pattern)) {
                        setSeriesStep(Float.parseFloat(pattern));
                    }else mView.getElementListModel().clear();
                } catch (Exception ex) {
                    mView.getElementListModel().clear();
                }
            }
            public void insertUpdate(DocumentEvent e) {
                try {
                    String pattern = mView.getSeriesStepInput().getText();
                    if(isValidLastSymbol(pattern)) {
                        setSeriesStep(Float.parseFloat(pattern));
                    }else mView.getElementListModel().clear();
                } catch (Exception ex) {
                    mView.getElementListModel().clear();
                }
            }
        });
    }
    private boolean isValidLastSymbol(String pattern){
        if(pattern.isEmpty()) return false;
        char lastChar = pattern.charAt(pattern.length() - 1);
        if((lastChar == '.') || (lastChar < '0' || lastChar < '9')) return true;
        return false;
    }


    private boolean mIsLiner;
    /** This method is responsible for changing series type */
    private void setSeriesType(boolean isLiner){
        if(isLiner == mIsLiner) return;
        mIsLiner = isLiner;
        if(isLiner){
            mSeries = new Liner(mSeries.getN(), mSeries.getFirstMember(), mSeries.getStep());
            mView.setSeriesStepName("d");
        }else{
            mSeries = new Exponential(mSeries.getN(), mSeries.getFirstMember(), mSeries.getStep());
            mView.setSeriesStepName("q");
        }
        updateElements();
    }

    private void setFirstElement(float firstElement){
        mSeries = mSeries.setFirstMember(firstElement);
        updateElements();
    }
    private void setNumberOfElements(int n){
        mSeries = mSeries.setN(n);
        updateElements();
    }

    private void setSeriesStep(float step){
        mSeries = mSeries.setStep(step);
        updateElements();
    }

    private void saveToFile(){
        final JFileChooser fc = new JFileChooser();
        if(fc.showSaveDialog(mView) == JFileChooser.APPROVE_OPTION){
            File file = new File(fc.getSelectedFile().getPath() + ".txt");
            try {
                file.createNewFile();
                mSeries.writeToFile(file);
            }catch (IOException ex){
                JOptionPane.showMessageDialog(mView, "ERROR: Unable to create file!");
            }
        }
    }

    private void showSum(){
        JOptionPane.showMessageDialog(mView, "sum = " + mSeries.getSum());
    }

    /**
     * Fills the list with elements to show them
     */
    private void updateElements(){
        DefaultListModel<String> listModel = mView.getElementListModel();
        listModel.clear();
        for(int i=1; i<=mSeries.getN(); i++){
            listModel.addElement(String.valueOf(mSeries.calculateJMember(i)));
        }
    }
}
