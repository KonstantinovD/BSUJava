package tasks.lab11.controller;

import javafx.stage.FileChooser;
import tasks.lab11.view.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileFilter;
import java.io.IOException;
import java.io.File;

public class Controller {

    private Frame mView;
    private Color mPaintColor;
    //private Graphics graphic;
    public Controller(){}

    private int x1, y1, x2, y2;

    public void setFrame(Frame frame){
        mView = frame;
        //initializeListeners();
        //setInitialAppState();
    }

/*

    public void initializeListeners(){

        mView.getDrawPanel().addMouseMotionListener(new DrawPanelListener());

        //mouse
        mView.getDrawPanel().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                x1 = mouseEvent.getX();
                y1 = mouseEvent.getY();
            }
        });
        //resize
        mView.getDrawPanel().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Image image = mView.getImage();
                if (image !=null) {
                    Image tempImage =  mView.createImage(Math.max(mView.getSize().width, image.getWidth(null)), Math.max(mView.getSize().height, image.getHeight(null)));
                    Graphics g = (Graphics) tempImage.getGraphics();
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, mView.getSize().width, mView.getSize().height);
                    g.drawImage(image, 0, 0, null);
                    image = tempImage;
                    graphicsOfImage = image.getGraphics();
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

                        graphicsOfImage = image.getGraphics();

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

    class DrawPanelListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent mouseEvent) {
            if (image == null) {
                makeImage();
            }
            graphicsOfPanel = (Graphics) drawPanel.getGraphics();
            graphicsOfImage.setColor(mColor);
            graphicsOfPanel.setColor(mColor);


            x2 = mouseEvent.getX();
            y2 = mouseEvent.getY();
            graphicsOfImage.drawLine(x1, y1, x2, y2);
            graphicsOfPanel.drawLine(x1,y1, x2, y2);
            x1 = mouseEvent.getX();
            y1 = mouseEvent.getY();
        }
    }



    private void setInitialAppState(){

    }

*/

}
