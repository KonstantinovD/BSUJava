package tasks.lab11.view;

import tasks.lab11.controller.Controller;
import tasks.lab11.view.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Collections;


public class Frame extends JFrame {
/*    private JButton mRedButton;
    private JButton mGreenButton;
    private JButton mBlueButton;

    private Color mColor;

    private JPanel drawPanel;
    private Graphics graphicsOfPanel,graphicsOfImage;
    private JPanel mButtonPanel;

    public JPanel getDrawPanel() {
        return drawPanel;
    }

    //menu
    private JMenuItem menuItemLoadImage;
    private JMenuItem menuItemSavefile;


    //image
    private Image image;

    public Image getImage() {
        return image;
    }

    public Frame(){
        setLayout(new BorderLayout());

        buildGUI();
        mColor = Color.RED;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    private void buildGUI(){
        drawPanel = new JPanel()  {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        drawPanel.setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        drawPanel.setPreferredSize(new Dimension(900,800));
        JScrollPane scrollPane = new JScrollPane(drawPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(500,500));
        add(scrollPane, BorderLayout.CENTER);
        //DrawPanelListener motionListener = new DrawPanelListener();
        //drawPanel.addMouseMotionListener(motionListener);


        mButtonPanel = new JPanel(new FlowLayout());
        createButtons(mButtonPanel);
        this.add(mButtonPanel, BorderLayout.NORTH);
        createMenu();
        pack();
    }

    private final int BUTTON_HEIGHT = 80;
    private final int BUTTON_WIDTH = 30;
    void createButtons(JPanel panel){
        mRedButton = new JButton("Red");
        mRedButton.setBackground(Color.RED);
        mRedButton.setPreferredSize(new Dimension(BUTTON_HEIGHT, BUTTON_WIDTH));
        mRedButton.setFocusable(false);

        mGreenButton = new JButton("Green");
        mGreenButton.setBackground(Color.GREEN);
        mGreenButton.setPreferredSize(new Dimension(BUTTON_HEIGHT, BUTTON_WIDTH));
        mGreenButton.setFocusable(false);

        mBlueButton = new JButton("Blue");
        mBlueButton.setBackground(Color.BLUE);
        mBlueButton.setForeground(Color.WHITE);
        mBlueButton.setPreferredSize(new Dimension(BUTTON_HEIGHT, BUTTON_WIDTH));
        mBlueButton.setFocusable(false);

        panel.add(mRedButton);
        panel.add(mGreenButton);
        panel.add(mBlueButton);
    }




    public void resizeDrawPanel(){
        drawPanel.setPreferredSize(this.getSize());
        drawPanel.repaint();
    }


    void makeImage(){
        image = createImage(drawPanel.getSize().width, drawPanel.getSize().height);
        graphicsOfImage = (Graphics) image.getGraphics();
        graphicsOfImage.setColor(Color.WHITE);
        graphicsOfImage.fillRect(0, 0, getSize().width, getSize().height);
    }

    void createMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("settings");
        menuBar.add(menu);
        menuItemLoadImage = new JMenuItem("load image");
        menuItemSavefile  = new JMenuItem("save image");

        menu.add(menuItemLoadImage);
        menu.add(menuItemSavefile);
        this.setJMenuBar(menuBar);
    }



*/
}
