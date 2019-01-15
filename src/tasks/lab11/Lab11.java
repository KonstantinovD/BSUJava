package tasks.lab11;

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


public class Lab11 extends JFrame {
    private JButton mRedButton;
    private JButton mGreenButton;
    private JButton mBlueButton;

    private Color mColor;

    private JPanel drawPanel;
    private Graphics panelGraphic, imageGraphic;
    private JPanel mButtonPanel;
    //menu
    private JMenuItem menuItemLoadImage;
    private JMenuItem menuItemSavefile;

    private int x1, y1, x2, y2;
    //image
    private Image image;

    private final int FRAME_HEIGHT = 500;
    private final int FRAME_WIDTH = 500;
    Lab11() {
        setLayout(new BorderLayout());

        buildGUI();
        mColor = Color.RED;

        initListeners();
        this.setSize(FRAME_HEIGHT, FRAME_WIDTH);
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
        DrawPanelListener motionListener = new DrawPanelListener();
        drawPanel.addMouseMotionListener(motionListener);
        drawPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

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

    public void initListeners(){
        //mouse
        drawPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                x1 = mouseEvent.getX();
                y1 = mouseEvent.getY();
            }
        });
        //resize
        drawPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if (image !=null) {
                    Image tempImage = createImage(Math.max(getSize().width, image.getWidth(null)), Math.max(getSize().height, image.getHeight(null)));
                    Graphics g = (Graphics) tempImage.getGraphics();
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, getSize().width, getSize().height);
                    g.drawImage(image, 0, 0, null);
                    image = tempImage;
                    imageGraphic = image.getGraphics();
                }
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeDrawPanel();
            }
        });

        //BUTTONS listeners
        mRedButton.addActionListener(e -> {
            mColor = Color.RED;
        });
        mGreenButton.addActionListener(e -> {
            mColor = Color.GREEN;
        });
        mBlueButton.addActionListener(e -> {
            mColor = Color.BLUE;
        });

        //menu items
        menuItemLoadImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
                JFileChooser fileChooser = new JFileChooser ();
                fileChooser.setCurrentDirectory(new File(".PNG"));

                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & GIF Images", "jpg", "gif");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(Lab11.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (file == null) {
                        return;
                    }
                    try {
                        Image img = ImageIO.read(file);

                        makeImage();
                        image.getGraphics().drawImage(img, 0, 0, null);

                        drawPanel.getGraphics().clearRect(0,0,drawPanel.getWidth(), drawPanel.getHeight());

                        imageGraphic = image.getGraphics();

                        drawPanel.getGraphics().drawImage(image, 0, 0, null);

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Unable to load image",
                                "Error",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });

        menuItemSavefile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser ();
                fileChooser.setCurrentDirectory(new File(".PNG"));

                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & GIF Images", "jpg", "gif");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showSaveDialog(Lab11.this);
                if (result == JFileChooser.APPROVE_OPTION){//Return value if approve (yes, ok) is chosen
                    File file = fileChooser.getSelectedFile();
                    if (file == null) {
                        return;
                    }
                    if (!file.getName().toLowerCase().endsWith(".JPG")) {
                        file = new File(file.getParentFile(), file.getName() + ".JPG");
                    }
                    try {
                        file.createNewFile();
                        file.setWritable(true);
                        ImageIO.write((RenderedImage) image,"jpg",file);
                    }
                    catch (IOException event) {
                        JOptionPane.showMessageDialog(null,
                                "Unable to save file",
                                "Error",
                                JOptionPane.PLAIN_MESSAGE);
                    }

                }
            }
        });
    }


    public void resizeDrawPanel(){
        drawPanel.setPreferredSize(this.getSize());
        drawPanel.repaint();
    }

    class DrawPanelListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent mouseEvent) {
            if (image == null) {
                makeImage();
            }
            panelGraphic = (Graphics) drawPanel.getGraphics();
            imageGraphic.setColor(mColor);
            panelGraphic.setColor(mColor);


            x2 = mouseEvent.getX();
            y2 = mouseEvent.getY();
            imageGraphic.drawLine(x1, y1, x2, y2);
            panelGraphic.drawLine(x1,y1, x2, y2);
            x1 = mouseEvent.getX();
            y1 = mouseEvent.getY();
        }
    }
    void makeImage(){
        image = createImage(drawPanel.getSize().width, drawPanel.getSize().height);
        imageGraphic = (Graphics) image.getGraphics();
        imageGraphic.setColor(Color.WHITE);
        imageGraphic.fillRect(0, 0, getSize().width, getSize().height);
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



    public static void main(String[] args) {
        EventQueue.invokeLater (new Runnable() {
            public void run(){
                Lab11 lab = new Lab11();
            }});
    }
}
