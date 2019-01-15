package tasks.lab13.view;

import tasks.lab13.student.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Frame extends JFrame {

    private JList mElementsList;
    private DefaultListModel<Student> mListModel;

    private JPanel rootPanel;

    private JButton openFileBtn;
    private JButton buildAlphabetListBtn;
    private JButton addNewStudentBtn;
    private JButton saveToXmlFileBtn;

    private final int FRAME_HEIGHT = 500;
    private final int FRAME_WIDTH = 300;
    public Frame(){
        super();

        buildGUI();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_HEIGHT, FRAME_WIDTH);
        this.setVisible(true);
    }

    private final int STATUS_PANEL_WIDTH = 16;
    private void buildGUI(){

        rootPanel = new JPanel(new BorderLayout());

        openFileBtn = new JButton("open file");
        buildAlphabetListBtn = new JButton("duplicates");
        addNewStudentBtn = new JButton("add student");
        saveToXmlFileBtn = new JButton("save to xml");
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(openFileBtn);
        btnPanel.add(buildAlphabetListBtn);
        btnPanel.add(addNewStudentBtn);
        btnPanel.add(saveToXmlFileBtn);

        mListModel = new DefaultListModel<>();
        mElementsList = new JList(mListModel);

        rootPanel.add(btnPanel, BorderLayout.NORTH);
        rootPanel.add(mElementsList,BorderLayout.CENTER);

        this.getContentPane().add(rootPanel);
    }

    public DefaultListModel<Student> getmListModel() {
        return mListModel;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getOpenFileBtn() {
        return openFileBtn;
    }

    public JButton getAddNewStudentBtn() {
        return addNewStudentBtn;
    }

    public JButton getSaveToXmlFileBtn() {
        return saveToXmlFileBtn;
    }

    public JButton getBuildAlphabetListBtn() {
        return buildAlphabetListBtn;
    }

    public void sendErrorMessage(String message){
        //custom title, error icon
        JOptionPane.showMessageDialog(this,
                message,
                "error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void setContent(List<Student> list){
        mListModel.clear();
        for(Student st : list){
            mListModel.addElement(st);
        }
    }


}
