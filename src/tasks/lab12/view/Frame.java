package tasks.lab12.view;

//import tasks.lab11.view.ColourfulPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame extends JFrame {

    private JPanel rootPanel1;
    private JPanel centerPanel;
    private JButton upperButton;
    private JButton lowerButton;

    private JList<String> leftList;
    private JList<String> rightList;

    private DefaultListModel<String> leftListModel;
    private DefaultListModel<String> rightListModel;

    private JPanel rootPanel2;
    private JPanel rootPanel3;


    JTabbedPane tabbedPane;


    private final int FRAME_HEIGHT = 350;
    private final int FRAME_WIDTH = 300;
    public Frame() {
        super();

        tabbedPane = new JTabbedPane();

        buildGUI();

        this.getContentPane().add(tabbedPane);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_HEIGHT, FRAME_WIDTH);
        this.setVisible(true);

        setInitialListsValues();
    }

    private final int LIST_WIDTH = 100;
    private final int LIST_HEIGHT = 100;
    private void buildGUI() {
        rootPanel1 = new JPanel(new BorderLayout());

        leftListModel = new DefaultListModel<>();
        rightListModel = new DefaultListModel<>();

        leftList = new JList<>(leftListModel);
        leftList.setPreferredSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));
        rightList = new JList<>(rightListModel);
        rightList.setPreferredSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));

        rootPanel1.add(leftList, BorderLayout.WEST);
        rootPanel1.add(rightList, BorderLayout.EAST);
        rootPanel1.add(buildCenterPanel(), BorderLayout.CENTER);

        tabbedPane.addTab("first", rootPanel1);




        rootPanel2 = new SecondPanel();
        tabbedPane.add("second", rootPanel2);

        rootPanel3 = new ThirdPanel();
        tabbedPane.add("third", rootPanel3);



        //this.getContentPane().add(rootPanel1);
    }

    private JPanel buildCenterPanel(){
        centerPanel = new JPanel(new BorderLayout());

        upperButton = new JButton(">>");
        lowerButton = new JButton("<<");

        centerPanel.add(upperButton, BorderLayout.NORTH);
        centerPanel.add(lowerButton, BorderLayout.SOUTH);

        return centerPanel;
    }

    private void setInitialListsValues(){
        leftListModel.addElement("My (left)");
        leftListModel.addElement("cat (left)");
        leftListModel.addElement("is (left)");
        leftListModel.addElement("black (left)");

        rightListModel.addElement("My (r)");
        rightListModel.addElement("dog (r)");
        rightListModel.addElement("is (r)");
        rightListModel.addElement("very (r)");
        rightListModel.addElement("big (r)");
    }

    public JPanel getRootPanel1() {
        return rootPanel1;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public JButton getUpperButton() {
        return upperButton;
    }

    public JButton getLowerButton() {
        return lowerButton;
    }

    public JList<String> getLeftList() {
        return leftList;
    }

    public JList<String> getRightList() {
        return rightList;
    }

    public DefaultListModel<String> getLeftListModel() {
        return leftListModel;
    }

    public DefaultListModel<String> getRightListModel() {
        return rightListModel;
    }
}



class SecondPanel extends JPanel {

    private Color ordinaryColor = new Color(213, 213, 100);
    private Color enteredColor = new Color(200, 21, 21);

    public SecondPanel(){
        this.setLayout(new GridLayout(8,8));
        MouseAdapter mouseAdapter = new MyMouseAdapter();
        //создаём кнопки добавляем слушаетеля к каждой(одного и того же), добавляем на панель
        for (int i=0;i<32;i++){
            JButton button = new JButton(i + "");
            button.setActionCommand(i + "");
            button.setBackground(ordinaryColor);
            button.addMouseListener(mouseAdapter);
            this.add(button);
        }
    }

    private class MyMouseAdapter extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            setColor(e, enteredColor);
        }
        public void mouseExited(MouseEvent e) {
            setColor(e, ordinaryColor);
            setText(e, ((JButton)e.getComponent()).getActionCommand());
        }
        public void mousePressed(MouseEvent e) {
            setText(e, "Clicked");
        }
        public void mouseReleased(MouseEvent e) {
            setText(e, ((JButton)e.getComponent()).getActionCommand());
        }

        private void setText(MouseEvent event, String text){
            ((JButton) event.getSource()).setText(text);
        }
        private void setColor(MouseEvent event, Color color){
            ((JButton)event.getSource()).setBackground(color);
        }
    }
}

class ThirdPanel extends JPanel {

    private ImageIcon img1, img2, img3, img4;

    ThirdPanel(){
        this.setLayout(new GridLayout(2,2));
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton radioButton;


        img1 = new ImageIcon("d:/DRES/img/1.jpg");
        img2 = new ImageIcon("d:/DRES/img/2.jpg");
        img3 = new ImageIcon("d:/DRES/img/3.jpg");
        img4 = new ImageIcon("d:/DRES/img/4.jpg");

        for (int i=0;i<4;i++){
            radioButton = new JRadioButton(img3);

            radioButton.setIcon(img3);
            radioButton.setRolloverIcon(img1);
            radioButton.setSelectedIcon(img2);
            //radioButton.setRolloverSelectedIcon(img3);
            radioButton.setPressedIcon(img4);

            buttonGroup.add(radioButton);
            this.add(radioButton);
        }
    }
}
