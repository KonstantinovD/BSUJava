package tasks.lab10.view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class Frame extends JFrame {

    private JPanel mRootPanel;
    private JPanel mStatusPanel;
    private JLabel mStatusLabel;

    private JButton mButton;

    private final int FRAME_HEIGHT = 350;
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
        mRootPanel = new JPanel(new BorderLayout());
        mRootPanel.setFocusable(true);

        mStatusPanel = new JPanel();
        mStatusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        mStatusPanel.setPreferredSize(new Dimension(FRAME_WIDTH, STATUS_PANEL_WIDTH));
        mStatusPanel.setLayout(new BoxLayout(mStatusPanel, BoxLayout.X_AXIS));
        mStatusLabel = new JLabel("");
        mStatusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        mStatusPanel.add(mStatusLabel);

        mButton = new JButton("");
        setButtonPosition(new Dimension(5,5));
        mButton.setMargin( new Insets(0, 0, 0, 0));

        JPanel centerPanel = new JPanel(null);
        centerPanel.add(mButton);

        mRootPanel.add(mStatusPanel, BorderLayout.SOUTH);
        mRootPanel.add(centerPanel, BorderLayout.CENTER);
        this.add(mRootPanel);
    }

    private final int BUTTON_WIDTH = 60;
    private final int BUTTON_HEIGHT = 30;
    public void setButtonPosition(Dimension d){
        mButton.setBounds(d.width, d.height, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    //getters
    public JPanel getStatusPanel() { return mStatusPanel; }
    public JLabel getStatusLabel() { return mStatusLabel; }
    public JPanel getRootPanel() { return mRootPanel; }
    public JButton getButton() { return mButton; }
}
