import java.io.IOException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class Vehicle {
   public static Image CAR = loadImage("c.png");
   public static Image BIKE = loadImage("b.png");
   public static Image BICYCLE = loadImage("mc.png");
   public static Image SPORTCAR = loadImage("sc.png");
   public static Image CAR_SELECTED = loadImage("c_selected.png");
   public static Image BIKE_SELECTED = loadImage("b_selected.png");
   public static Image BICYCLE_SELECTED = loadImage("mc_selected.png");
   public static Image SPORTCAR_SELECTED = loadImage("sc_selected.png");

   private final int ID;

   protected boolean isSelected;
   protected Image[] models;
   protected int height;
   protected int width;
   protected int x;
   protected int y;
   
   Vehicle() {
      this.x = 0;
      this.y = 0;
      this.width = 64;
      this.height = 64;
      this.isSelected = false;
      this.ID = generateHash();
      this.models = new Image[2];
      this.models[0] = Vehicle.CAR;
      this.models[1] = Vehicle.CAR_SELECTED;
   }

   public void draw(Graphics graphics, Road road) {
      Image current;

      if (this.isSelected) {
         current = this.models[1];
      } else {
         current = this.models[0];
      }

      graphics.drawImage(current, x * width, y * height, width, height, road);
   }

   public boolean isMouseOver(int x, int y) {
      return (this.x <= x && this.x + this.width >= x) && (this.y <= y && this.y + this.height >= y);
   }

   public void setSelected(boolean state) {
      this.isSelected = state;
   }

   public boolean isSelected() {
      return this.isSelected;
   }

   public Vehicle setPosition(int x, int y) {
      this.x = x;
      this.y = y;

      return this;
   }
   
   private static Image loadImage(String file) {
      Image image;

      try {
         image = ImageIO.read(new File("src/" + file));
      }
      catch(IOException e) {
         System.err.println("Falha ao carregar imagem.");
         image = null;
      }

      return image;
   }

   public int getID() {
      return this.ID;
   }

   private int generateHash() {
      int hash = this.hashCode() % 100000;

      for (int i = 0; i < 4; i++) {
         //hash += this.TIRES[i].hashCode() % 100000;
         hash = (int) Math.floor(hash / 7);
      }

      hash = Math.abs(hash) % 1000;

      return hash;
   }
}
