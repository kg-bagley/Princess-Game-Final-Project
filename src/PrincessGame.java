import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PrincessGame {

   public PrincessGame() {
   }

   static BufferedImage background;
   static BufferedImage layer;
   static BufferedImage outline;
   static BufferedImage bowImage;

   public static void main(String[] args) throws IOException {

      String backgroundFileName = "Castle.jpg";
      background = ImageIO.read(PrincessGame.class.getResource("/" + backgroundFileName));
      System.out.println("The width is " + background.getWidth());
      System.out.println("The height is " + background.getHeight());

      String layerFileName = "Princess Room.jpg";
      layer = ImageIO.read(PrincessGame.class.getResource("/" + layerFileName));
      System.out.println("The width is " + layer.getWidth());
      System.out.println("The height is " + layer.getHeight());

      String princessOutline = "CartoonPrincess2.png";
      outline = ImageIO.read(PrincessGame.class.getResource("/" + princessOutline));
      System.out.println("The width is " + outline.getWidth());
      System.out.println("The height is " + outline.getHeight());

      String theBowImage = "Bow.png";
      bowImage = ImageIO.read(PrincessGame.class.getResource("/" + theBowImage));
      System.out.println("The width is " + outline.getWidth());
      System.out.println("The height is " + outline.getHeight());

      // Create a Panel object
      Panel panel = new Panel(layer, background, outline, bowImage);

      // Call the setFrame method on that object
      panel.setFrame(layer, background, outline, bowImage);
   }
}
