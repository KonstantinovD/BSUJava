package lab11;

import com.sun.deploy.cache.CacheEntry;
import javafx.stage.FileChooser;
import javafx.stage.Screen;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

import static java.awt.Font.PLAIN;

public class DrawingApplication extends JFrame {
    private JRadioButton redButton;
    private JRadioButton greenButton;
    private JRadioButton blueButton;
    private JPanel drawPanel = new JPanel()  {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }
    };;
    private Graphics graphicsOfPanel,graphicsOfImage;
    //coordinates of a mouse
    private int previousX,previousY,currentX,currentY;
    //image with drawings
    private Image image;
    //constructor
    DrawingApplication() {
        setLayout(new BorderLayout());
        //инициализируем радиокнопки
        //RED
        ButtonGroup group = new ButtonGroup();
        redButton = new JRadioButton("Red");
        redButton.setFont(new Font("Serif", PLAIN, 50));
        redButton.setForeground(Color.RED);
        redButton.setBackground(new Color(255, 150, 150));
        //GREEN
        greenButton = new JRadioButton("Green");
        greenButton.setFont(new Font("Serif", PLAIN, 50));
        greenButton.setForeground(Color.GREEN);//color of letters
        greenButton.setBackground(new Color(150, 255, 150));//background color
        //BLUE
        blueButton = new JRadioButton("Blue");
        blueButton.setFont(new Font("Serif", PLAIN, 50));
        blueButton.setForeground(Color.BLUE);
        blueButton.setBackground(new Color(150, 150, 255));

        //добавляем в одну группу
        group.add(redButton);
        group.add(greenButton);
        group.add(blueButton);
        redButton.setSelected(true);//выбираем красную

        //добавляем кнопки на панель
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(redButton);
        buttonsPanel.add(greenButton);
        buttonsPanel.add(blueButton);
        //добавляем панель кнопок на окно(+выбираем цвет)
        buttonsPanel.setBackground(Color.LIGHT_GRAY);
        add(buttonsPanel, BorderLayout.NORTH);
        //инициализируем панель на которой будем рисовать

        //добавляем панель рисования на окно(+выбираем цвет)
        drawPanel.setBackground(Color.WHITE);
        //Size of Screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        drawPanel.setPreferredSize(new Dimension(900,800));
        JScrollPane scrollPane = new JScrollPane(drawPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(500,500));
        add(scrollPane, BorderLayout.CENTER);
        DrawPanelListener motionListener = new DrawPanelListener();
        drawPanel.addMouseMotionListener(motionListener);

        //Listener of Press
        drawPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                //save coordinates
                previousX = mouseEvent.getX();
                previousY = mouseEvent.getY();
            }
        });

        //Resizing of window
        drawPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if (image !=null) {
                    //создаём временное изображение с максимальным размер
                    Image tempImage = createImage(Math.max(getSize().width, image.getWidth(null)), Math.max(getSize().height, image.getHeight(null)));
                    Graphics g = (Graphics) tempImage.getGraphics();
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, getSize().width, getSize().height);
                    g.drawImage(image, 0, 0, null);//рисуем на временное наше image
                    image = tempImage;//resaving image
                    graphicsOfImage = image.getGraphics();
                }
            }
        });
        makeMenu();
        pack();
    }
    //Drawing
    class DrawPanelListener extends MouseMotionAdapter  {
        public void mouseDragged(MouseEvent mouseEvent) {
            if (image == null) {
                makeImage();
            }

            graphicsOfPanel = (Graphics) drawPanel.getGraphics();
            if (redButton.isSelected()) {
                graphicsOfImage.setColor(Color.RED);
                graphicsOfPanel.setColor(Color.RED);
            }
            else if (greenButton.isSelected()){
                graphicsOfImage.setColor(Color.GREEN);
                graphicsOfPanel.setColor(Color.GREEN);
            }
            else if (blueButton.isSelected()) {
                graphicsOfImage.setColor(Color.BLUE);
                graphicsOfPanel.setColor(Color.BLUE);
            }
            //rewrite new coordinates
            currentX = mouseEvent.getX();
            currentY = mouseEvent.getY();
            //drawing on the  panel and image
            graphicsOfImage.drawLine(previousX,previousY,currentX,currentY);
            graphicsOfPanel.drawLine(previousX,previousY,currentX,currentY);
            //save coordinates
            previousX = mouseEvent.getX();
            previousY = mouseEvent.getY();
        }
    }
    //constructor of image
    protected void makeImage(){
        //инициализируем image и заливаем его белым цветом
        image = createImage(getSize().width, getSize().height);
        graphicsOfImage = (Graphics) image.getGraphics();
        graphicsOfImage.setColor(Color.WHITE);
        graphicsOfImage.fillRect(0, 0, getSize().width, getSize().height);
    }
    //constructor of menu
    private void makeMenu() {
           JMenuBar menuBar = new JMenuBar();
           //menu
           JMenu fileMenu = new JMenu("File");
           //Items of menu
           JMenuItem  openFileItem  = new JMenuItem("Open");
           //Open file
           openFileItem.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   //
                   JFileChooser fileChooser = new JFileChooser ();
                   fileChooser.setCurrentDirectory(new File(".PNG"));
                   //файлы которые сможем открывать
                   FileNameExtensionFilter filter = new FileNameExtensionFilter(
                           "JPG & GIF Images", "jpg", "gif");
                   fileChooser.setFileFilter(filter);

                   // показать диалоговое окно для выбора файлов
                   int result = fileChooser.showOpenDialog(DrawingApplication.this);
                   if (result == JFileChooser.APPROVE_OPTION) {//Return value if approve (yes, ok) is chosen
                       File file = fileChooser.getSelectedFile();
                       if (file == null) {
                           return;
                       }
                       try {
                           Image img = ImageIO.read(file);
                           //if image wasn't made, make image
                           if (image == null) {
                              makeImage();
                           }
                           image = img;
                           //Graphics g = image.getGraphics();
                           graphicsOfImage = image.getGraphics();
                           //g.drawImage(img, 0, 0, ((BufferedImage) img).getWidth(),((BufferedImage) img).getHeight());

                           //g = drawPanel.getGraphics();
                           //g.drawImage(img, 0, 0, null);
                           drawPanel.getGraphics().drawImage(img, 0, 0, null);

                       } catch (IOException ex) {
                           JOptionPane.showMessageDialog(null,
                                   "Wrong file",
                                   "Error",
                                   JOptionPane.PLAIN_MESSAGE);
                      }
                   }
               }
           });
           fileMenu.add(openFileItem);
           //Save File
           JMenuItem  saveFileItem  = new JMenuItem("Save");
           saveFileItem.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   JFileChooser fileChooser = new JFileChooser ();
                   fileChooser.setCurrentDirectory(new File(".PNG"));
                   //файлы которые сможем открывать
                   FileNameExtensionFilter filter = new FileNameExtensionFilter(
                           "JPG & GIF Images", "jpg", "gif");
                   fileChooser.setFileFilter(filter);

                   // показать диалоговое окно для выбора файлов
                   int result = fileChooser.showSaveDialog(DrawingApplication.this);
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
                                   "Wrong file",
                                   "Error",
                                   JOptionPane.PLAIN_MESSAGE);
                       }

                   }
               }
           });
           fileMenu.add(saveFileItem);

           menuBar.add(fileMenu);
           this.setJMenuBar(menuBar);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater (new Runnable() {
            public void run(){
                DrawingApplication app = new DrawingApplication();
                //app.setExtendedState(JFrame.MAXIMIZED_BOTH);
                app.setTitle("Application");
                app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                app.setVisible(true);
            }});
    }
}
