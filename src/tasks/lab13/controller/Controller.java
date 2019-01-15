package tasks.lab13.controller;

import tasks.lab13.model.Model;
import tasks.lab13.student.Student;
import tasks.lab13.view.Frame;
import tasks.lab13.view.StudentEditDialog;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import  java.io.File;

public class Controller {
    private Frame view;
    private Model model;


    public void setFrame(Frame view) {
        this.view = view;
        initializeListeners();
        setInitialAppState();
    }

    public void setModel(Model model) {
        this.model = model;
    }

    private void initializeListeners(){
        view.getOpenFileBtn().addActionListener(e -> {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("txt & xml", "txt", "xml");
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(filter);
            if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                if (file != null) {
                    model.loadFile(file);
                }
            }else{
                model.showAllContent();
            }
        });

        view.getBuildAlphabetListBtn().addActionListener(e -> {
            model.buildDuplicates();
        });

        view.getAddNewStudentBtn().addActionListener(e -> {
            StudentEditDialog dialog = new StudentEditDialog();
            if(dialog.showConfirmDialog() == JOptionPane.OK_OPTION){

                try{
                    Student student = new Student();
                    student.setSecondName(dialog.getStudentNameEdit().getText());
                    student.setRecordBookNumber(Integer.parseInt(dialog.getRecordBookNumberEdit().getText()));
                    student.setYear(Integer.parseInt(dialog.getStudentYearEdit().getText()));
                    student.setGroup(Integer.parseInt(dialog.getStudentGroupEdit().getText()));



                    model.add(student);
                }catch (Exception ex){
                    view.sendErrorMessage("Unable to create student");
                }
            }
        });

        view.getSaveToXmlFileBtn().addActionListener(e -> {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files", "xml");
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(filter);
            if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                if (file != null) {
                    model.saveFileToXml(file);
                }
            }
        });
    }

    private void setInitialAppState(){

    }




}
