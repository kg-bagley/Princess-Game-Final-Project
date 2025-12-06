import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Font;

public class Panel extends JPanel {
    private BufferedImage background;
    private BufferedImage layer;
    private BufferedImage outline;
    private JButton roomButton;

    private JLayeredPane layeredPane;
    private JLabel hairLabel;
    private JLabel shirtLabel;
    private JLabel middleLabel;
    private JLabel sideLabel;
    private JLabel skinLabel;
    private JLabel shoeLabel;
    private JLabel bowLabel;
    private boolean layerOnTop = true;
    private JLabel gameLabel;
    private Font displayFont;

    private BufferedImage hairImage;
    private BufferedImage tintedHair;

    private BufferedImage shirtImage;
    private BufferedImage tintedShirt;

    private BufferedImage middleImage;
    private BufferedImage tintedMiddle;

    private BufferedImage sideImage;
    private BufferedImage tintedSide;

    private BufferedImage skinImage;
    private BufferedImage tintedSkin;

    private BufferedImage shoeImage;
    private BufferedImage tintedShoe;

    private BufferedImage bowImage;

    private JButton hairColorButton;
    private JButton shirtColorButton;
    private JButton middleColorButton;
    private JButton sideColorButton;
    private JButton skinColorButton;
    private JButton shoeColorButton;

    public Panel(BufferedImage image, BufferedImage image2, BufferedImage Image3, BufferedImage Image4) {

        this.background = image;
        this.layer = image2;
        this.outline = Image3;
        this.bowImage = Image4;
        setPreferredSize(new Dimension(1800, 880));
        // This sets the size for the background/layer pictures in the frame

        roomButton = new JButton("->");
        add(roomButton);
        NextRoomListener ButtonListener = new NextRoomListener();
        roomButton.addActionListener(ButtonListener);

        hairColorButton = new JButton("Change Hair Color");
        add(hairColorButton);
        ColorSelectListener csListener = new ColorSelectListener();
        hairColorButton.addActionListener(csListener);

        shirtColorButton = new JButton("Change Shirt Color");
        add(shirtColorButton);
        ColorSelectListener sListener = new ColorSelectListener();
        shirtColorButton.addActionListener(sListener);

        middleColorButton = new JButton("Change Sleeves Color");
        add(middleColorButton);
        ColorSelectListener mListener = new ColorSelectListener();
        middleColorButton.addActionListener(mListener);

        sideColorButton = new JButton("Change Sides' Color");
        add(sideColorButton);
        ColorSelectListener siListener = new ColorSelectListener();
        sideColorButton.addActionListener(siListener);

        skinColorButton = new JButton("Change Skin Color");
        add(skinColorButton);
        ColorSelectListener skListener = new ColorSelectListener();
        skinColorButton.addActionListener(skListener);

        shoeColorButton = new JButton("Change Shoe Color");
        add(shoeColorButton);
        ColorSelectListener shListener = new ColorSelectListener();
        shoeColorButton.addActionListener(shListener);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1800, 880));
        add(layeredPane);

        hairLabel = new JLabel("");
        hairLabel.setBounds(783, 413, 250, 300);
        hairLabel.setOpaque(false);
        hairUpdateColor();

        shirtLabel = new JLabel("");
        shirtLabel.setBounds(870, 470, 250, 300);
        shirtLabel.setOpaque(false);
        shirtUpdateColor();

        middleLabel = new JLabel("");
        middleLabel.setBounds(800, 380, 250, 450);
        middleLabel.setOpaque(false);
        middleUpdateColor();

        sideLabel = new JLabel("");
        sideLabel.setBounds(800, 380, 250, 450);
        sideLabel.setOpaque(false);
        sideUpdateColor();

        skinLabel = new JLabel("");
        skinLabel.setBounds(800, 383, 250, 450);
        skinLabel.setOpaque(false);
        skinUpdateColor();

        shoeLabel = new JLabel("");
        shoeLabel.setBounds(800, 357, 250, 500);
        shoeLabel.setOpaque(false);
        shoeUpdateColor();

        bowLabel = new JLabel("");
        bowLabel.setBounds(810, 438, 80, 50);
        bowLabel.setOpaque(false);
        EventListener listener = new EventListener();
        bowLabel.addMouseListener(listener);
        bowLabel.addMouseMotionListener(listener);

        gameLabel = new JLabel("Decorate the Princess!");
        gameLabel.setBounds(790, 10, 600, 20);
        gameLabel.setOpaque(false);

        displayFont = new Font("Arial", Font.BOLD, 20);
        gameLabel.setFont(displayFont);

        try {
            hairImage = ImageIO.read(getClass().getResource("/WhiteHair3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        // I had to use a try/catch block because ImageIO has lots of potential errors
        // and Java wants to
        // make sure the program doesn't mess up if those errors occur

        try {
            shirtImage = ImageIO.read(getClass().getResource("/front of dress.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            middleImage = ImageIO.read(getClass().getResource("/MiddleofDress6.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            sideImage = ImageIO.read(getClass().getResource("/SidesofDress.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            skinImage = ImageIO.read(getClass().getResource("/Skin.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            shoeImage = ImageIO.read(getClass().getResource("/Shoes.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        tintedHair = tintImage(hairImage, Color.BLACK); // Tint the original hair to black
        hairLabel.setIcon(new ImageIcon(tintedHair));

        tintedShirt = tintImage(shirtImage, Color.blue);
        shirtLabel.setIcon(new ImageIcon(tintedShirt));

        tintedMiddle = tintImage(middleImage, Color.PINK);
        middleLabel.setIcon(new ImageIcon(tintedMiddle));

        tintedSide = tintImage(sideImage, Color.GREEN);
        sideLabel.setIcon(new ImageIcon(tintedSide));

        tintedSkin = tintImage(skinImage, Color.orange);
        skinLabel.setIcon(new ImageIcon(tintedSkin));

        tintedShoe = tintImage(shoeImage, Color.gray);
        shoeLabel.setIcon(new ImageIcon(tintedShoe));

        bowLabel.setIcon(new ImageIcon(bowImage));

        // Makes sure the hair is on top of everything else

        layeredPane.add(hairLabel, Integer.valueOf(1));
        layeredPane.add(shirtLabel, Integer.valueOf(1));
        layeredPane.add(middleLabel, Integer.valueOf(10));
        layeredPane.add(sideLabel, Integer.valueOf(20));
        layeredPane.add(skinLabel, Integer.valueOf(30));
        layeredPane.add(shoeLabel, Integer.valueOf(40));
        layeredPane.add(bowLabel, Integer.valueOf(60));
        layeredPane.add(gameLabel, Integer.valueOf(70));

        layeredPane.setFocusable(true);
        layeredPane.requestFocusInWindow();
    }

    public void setFrame(BufferedImage image, BufferedImage image2, BufferedImage image3, BufferedImage Image4) {
        JFrame myFrame = new JFrame("Game");

        JPanel primary = new JPanel();
        myFrame.getContentPane().add(primary);
        Panel panel = new Panel(image, image2, image3, Image4);
        primary.add(panel);
        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private BufferedImage tintImage(BufferedImage src, Color tint) {

        // Create a new empty image with the same size as the source.
        // TYPE_INT_ARGB lets us store transparency (important for PNG hair edges).
        BufferedImage tinted = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                // these loops read all the pixels in the image

                // Reads the pixels from the original image
                // Each pixel has alpha, red, green, and blue
                int argb = src.getRGB(x, y);

                // Deals with just the alpha (transparency) component.
                int alpha = (argb >> 24) & 0xff;
                // alpha relates to transparency

                if (alpha == 0) {
                    // The above line has all transparent pixels stay unchanged
                    tinted.setRGB(x, y, argb); // Copy the pixel as it is
                    continue;
                }

                int r = tint.getRed();
                int g = tint.getGreen();
                int b = tint.getBlue();
                // New color values from the tint

                int newColor = (alpha << 24) | (r << 16) | (g << 8) | b;
                // the numbers are bit positions
                // Above line replaces the color with the new tint

                // Stores the recolored pixels inside the new "tinted" image.
                tinted.setRGB(x, y, newColor);
            }
        }
        return tinted;
    }

    public void hairUpdateColor() {
    }

    public void shirtUpdateColor() {
    }

    public void middleUpdateColor() {
    }

    public void sideUpdateColor() {
    }

    public void skinUpdateColor() {
    }

    public void shoeUpdateColor() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (layerOnTop) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
            g.drawImage(layer, 0, 0, getWidth(), getHeight(), null);
            // if true then the background is drawn first
        } else {
            g.drawImage(layer, 0, 0, getWidth(), getHeight(), null);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
            // OR if it's false then the layer is drawn first
        }
        g.drawImage(outline, 800, 410, null);
        g.drawImage(bowImage, 0, 0, null);
    }

    private Point bowLocation = new Point(100, 100);
    private Point startPoint;

    public void bowUpdateLocation() {
        bowLabel.setLocation(bowLocation);
    }

    private class EventListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent event) {
            System.out.println("Mouse Click");
        }

        @Override
        public void mouseEntered(MouseEvent event) {
            System.out.println("Mouse Entered");
        }

        @Override
        public void mouseExited(MouseEvent event) {
            System.out.println("Mouse Exited");
        }

        @Override
        public void mousePressed(MouseEvent event) {
            System.out.println("Mouse pressed");
            startPoint = event.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            System.out.println("Mouse Released");
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            // Figure out where the mouse is on the screen
            Point screenLocation = event.getLocationOnScreen();
            // Figure out where the panel is on the screen
            Point panelLocation = getLocationOnScreen();
            // Figure out where the bow should go
            bowLocation = new Point(screenLocation.x - panelLocation.x - startPoint.x,
                    screenLocation.y - panelLocation.y - startPoint.y);
            // Gives smooth motion to move bow
            bowUpdateLocation();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // e = event
            System.out.println("Mouse Moved" + e.getPoint());

        }

    }

    private class NextRoomListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // true then false, false then true
            layerOnTop = !layerOnTop;
            // this is kind of like updateIndex. It actually changes the image
            repaint();
        }
    }

    public class ColorSelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
            JButton button = (JButton) e.getSource();

            if (newColor != null && button == hairColorButton) {
                tintedHair = tintImage(hairImage, newColor);
                hairLabel.setIcon(new ImageIcon(tintedHair));
            }

            if (newColor != null && button == shirtColorButton) {
                tintedShirt = tintImage(shirtImage, newColor);
                shirtLabel.setIcon(new ImageIcon(tintedShirt));
            }
            if (newColor != null && button == middleColorButton) {
                tintedMiddle = tintImage(middleImage, newColor);
                middleLabel.setIcon(new ImageIcon(tintedMiddle));
            }
            if (newColor != null && button == sideColorButton) {
                tintedSide = tintImage(sideImage, newColor);
                sideLabel.setIcon(new ImageIcon(tintedSide));
            }
            if (newColor != null && button == skinColorButton) {
                tintedSkin = tintImage(skinImage, newColor);
                skinLabel.setIcon(new ImageIcon(tintedSkin));
            }

            if (newColor != null && button == shoeColorButton) {
                tintedShoe = tintImage(shoeImage, newColor);
                shoeLabel.setIcon(new ImageIcon(tintedShoe));
            }
        }
    }

}