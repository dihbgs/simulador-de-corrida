import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Vehicle {
   public static Image CAR;
   public static Image CAR_SELECTED;
   public static Image SPORTCAR;
   public static Image SPORTCAR_SELECTED;
   public static Image BICYCLE;
   public static Image BICYCLE_SELECTED;
   public static Image BIKE;
   public static Image BIKE_SELECTED;

   private boolean isSelected;
   private Image[] models;
   private int height;
   private int width;
   private int x;
   private int y;

   Vehicle(int x, int y, int width, int height) {
      try {
         this.model = ImageIO.read(new File("src/c.png"));
      } catch (IOException e) {

      }
      this.isSelected = false;
      this.height = height;
      this.width = width;
      this.x = x;
      this.y = y;
   }

   public void draw(Graphics graphics, Road road) {
      Image current;

      if (this.isSelected) {
         current = this.model[0];
      } else {
         current = this.model[1];
      }

      graphics.drawImage(model, x, y, width, height, road);
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

   // private int generateHash() {
   // int hash = this.hashCode() % 100000;

   // for (int i = 0; i < 4; i++) {
   // hash += this.TIRES[i].hashCode() % 100000;
   // hash = (int) Math.floor(hash / 7);
   // }

   // hash = Math.abs(hash) % 1000;

   // return hash;
   // }
}
