package tasks.test3.view;

import tasks.test3.model.Employee;
import tasks.test3.model.Human;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View extends JFrame {

    private JList elementsList;
    private DefaultListModel<String> listModel;

    private JPanel rootPanel;

    private JMenuItem menuItemOpenFile;

    private JMenuItem menuItemSortByPosition;
    private JMenuItem menuItemSortBySalary;
    private JMenuItem menuItemEqualSecondNames;
    private JMenuItem menuItemShowPositions;
    private JMenuItem menuItemFindBetterWorker;


    private final int FRAME_HEIGHT = 600;
    private final int FRAME_WIDTH = 300;
    public View(){
        super();
        buildGUI();



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_HEIGHT, FRAME_WIDTH);
        this.setVisible(true);
    }

    private void buildGUI() {
        rootPanel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel<>();
        elementsList = new JList(listModel);
        createMenu();

        rootPanel.add(elementsList,BorderLayout.CENTER);

        this.getContentPane().add(rootPanel);
    }


    void createMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Data");
        menuBar.add(menu1);
        menuBar.add(menu2);

        menuItemOpenFile = new JMenuItem("open file");

        menuItemSortByPosition = new JMenuItem("sort by position");
        menuItemSortBySalary = new JMenuItem("sort by salary");
        menuItemEqualSecondNames = new JMenuItem("find people with equal second names");
        menuItemShowPositions = new JMenuItem("show positions");
        menuItemFindBetterWorker = new JMenuItem("find better worker than the worst");

        menu1.add(menuItemOpenFile);

        menu2.add(menuItemSortByPosition);
        menu2.add(menuItemSortBySalary);
        menu2.add(menuItemEqualSecondNames);
        menu2.add(menuItemShowPositions);
        menu2.add(menuItemFindBetterWorker);

        this.setJMenuBar(menuBar);
    }

    public JMenuItem getMenuItemOpenFile() {
        return menuItemOpenFile;
    }

    public JMenuItem getMenuItemSortByPosition() {
        return menuItemSortByPosition;
    }

    public JMenuItem getMenuItemSortBySalary() {
        return menuItemSortBySalary;
    }

    public JMenuItem getMenuItemEqualSecondNames() {
        return menuItemEqualSecondNames;
    }

    public JMenuItem getMenuItemShowPositions() {
        return menuItemShowPositions;
    }

    public JMenuItem getMenuItemFindBetterWorker() {
        return menuItemFindBetterWorker;
    }

    public void sendErrorMessage(String message){
        //custom title, error icon
        JOptionPane.showMessageDialog(this,
                message,
                "error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void setContent(List<? extends Human> list){
        listModel.clear();
        for(Human st : list){
            listModel.addElement(st.toString());
        }
    }

    public void showPos(List<Employee.Position> list){
        listModel.clear();
        for(Employee.Position p : list){
            listModel.addElement(p.toString());
        }
    }
}
