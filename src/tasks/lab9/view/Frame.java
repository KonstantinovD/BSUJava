package tasks.lab9.view;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private JList mElementsList;
    private DefaultListModel<String> mListModel;
    private JScrollPane mScroll;

    private JTextField mFirstElemInput;
    private JTextField mSeriesStepInput;
    private JTextField mNumberOfElementsInput;

    private JRadioButton mLinerRadioBtn;
    private JRadioButton mExponentRadioBtn;

    private JButton mSaveToFileBtn;
    private JButton mCalculateSumBtn;

    private JLabel mSeriesStepName;


    private final int FRAME_HEIGHT = 350;
    private final int FRAME_WIDTH = 300;
    public Frame(){
        super();

        buildGUI();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_HEIGHT, FRAME_WIDTH);
        this.setVisible(true);
    }

    private final int ELEMENTLIST_WIDTH = 200;
    private void buildGUI(){
        mListModel = new DefaultListModel<>();
        mElementsList = new JList(mListModel);

        mElementsList.setVisibleRowCount(10);
        mElementsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        mScroll = new JScrollPane(mElementsList);


        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buildLeftPanel(), BorderLayout.WEST);
        panel.add(mScroll, BorderLayout.CENTER);

        this.getContentPane().add(panel);
    }

    private final int LEFT_PANEL_WIDTH = 100;
    private final int TEXT_FIELD_HEIGHT = 20;
    private final int BUTTON_HEIGHT = 25;
    private JPanel buildLeftPanel(){
        JPanel leftPanel = new JPanel(new FlowLayout());
        buildRadioGroup();

        mSaveToFileBtn = new JButton("save to file");
        mSaveToFileBtn.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, BUTTON_HEIGHT));
        mCalculateSumBtn = new JButton("sum");
        mCalculateSumBtn.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, BUTTON_HEIGHT));

        mFirstElemInput = new JTextField();
        mFirstElemInput.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, TEXT_FIELD_HEIGHT));
        mSeriesStepInput = new JTextField();
        mSeriesStepInput.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, TEXT_FIELD_HEIGHT));
        mNumberOfElementsInput = new JTextField();
        mNumberOfElementsInput.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, TEXT_FIELD_HEIGHT));

        leftPanel.add(mLinerRadioBtn);
        leftPanel.add(mExponentRadioBtn);
        leftPanel.add(mSaveToFileBtn);
        leftPanel.add(mCalculateSumBtn);

        leftPanel.add(new JLabel("First element"));
        leftPanel.add(mFirstElemInput);
        mSeriesStepName = new JLabel(" ");
        leftPanel.add(mSeriesStepName);
        leftPanel.add(mSeriesStepInput);
        leftPanel.add(new JLabel("N"));
        leftPanel.add(mNumberOfElementsInput);

        leftPanel.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, FRAME_HEIGHT));
        return leftPanel; //leftPanel.setBackground(new Color(100,100, 10)); for (Component c: leftPanel.getComponents()) c.setBackground(new Color(100,100, 10));
    }
    private ButtonGroup buildRadioGroup(){
         ButtonGroup group = new ButtonGroup();

         mLinerRadioBtn = new JRadioButton("Liner");
         mLinerRadioBtn.setActionCommand("lin");

         mExponentRadioBtn = new JRadioButton("Exponential");
         mExponentRadioBtn.setActionCommand("exp");

         group.add(mLinerRadioBtn);
         group.add(mExponentRadioBtn);
         return group;
    }

    //getters
    public DefaultListModel<String> getElementListModel() {
        return mListModel;
    }
    public JTextField getSeriesStepInput() {
        return mSeriesStepInput;
    }
    public JTextField getFirstElemInput() {
        return mFirstElemInput;
    }
    public JTextField getNumberOfElementsInput() {
        return mNumberOfElementsInput;
    }
    public JButton getSaveToFileBtn() {
        return mSaveToFileBtn;
    }
    public JButton getCalculateSumBtn() { return mCalculateSumBtn; }
    public JRadioButton getLinerRadioBtn() {
        return mLinerRadioBtn;
    }
    public JRadioButton getExponentRadioBtn() {
        return mExponentRadioBtn;
    }
    //setters
    public void setSeriesStepName(String name){
        mSeriesStepName.setText(name);
    }
}
