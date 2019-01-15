package tasks.lab13.view;

import javax.swing.*;
import java.awt.*;

public class StudentEditDialog {
    private JTextField studentNameEdit;
    private JTextField recordBookNumberEdit;
    private JTextField studentYearEdit;
    private JTextField studentGroupEdit;
    private JPanel mPanel;

    private final int STRING_EDIT_SIZE = 15;
    private final int NUMBER_EDIT_SIZE = 15;
    public StudentEditDialog(){
        studentNameEdit = new JTextField(STRING_EDIT_SIZE);
        recordBookNumberEdit = new JTextField(NUMBER_EDIT_SIZE);
        studentYearEdit = new JTextField(NUMBER_EDIT_SIZE);
        studentGroupEdit = new JTextField(NUMBER_EDIT_SIZE);
        mPanel = new JPanel(new FlowLayout());
        mPanel.add(new JLabel("name:"));
        mPanel.add(studentNameEdit);
        mPanel.add(Box.createHorizontalStrut(2)); // a spacer
        mPanel.add(new JLabel("book number:"));
        mPanel.add(recordBookNumberEdit);
        mPanel.add(Box.createHorizontalStrut(2));
        mPanel.add(new JLabel("year:"));
        mPanel.add(studentYearEdit);
        mPanel.add(Box.createVerticalStrut(2));
        mPanel.add(new JLabel("group:"));
        mPanel.add(studentGroupEdit);
        mPanel.add(Box.createHorizontalStrut(2));
    }

    public int showConfirmDialog(){
        return JOptionPane.showConfirmDialog(null, mPanel,
                "Enter canvas size", JOptionPane.OK_CANCEL_OPTION);
    }

    public JTextField getStudentNameEdit() {
        return studentNameEdit;
    }

    public JTextField getRecordBookNumberEdit() {
        return recordBookNumberEdit;
    }

    public JTextField getStudentYearEdit() {
        return studentYearEdit;
    }

    public JTextField getStudentGroupEdit() {
        return studentGroupEdit;
    }
}
