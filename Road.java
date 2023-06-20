import java.util.ArrayList;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Road extends JComponent implements MouseListener, ActionListener {
   private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
   private int width;
   private int height;

   public Road() {
      this.width = 100;
      this.height = 100;

      addMouseListener(this);
   }

   protected void paintComponent(Graphics context) {
      super.paintComponent(context);

      Graphics2D graphics = (Graphics2D) context;
      graphics.setColor(new Color(200, 200, 200));

      for (int row = 0; row < this.width; row += 16) {
         graphics.drawLine(row, 0, row, this.height);
      }
      for (int col = 0; col < this.height; col += 16) {
         graphics.drawLine(0, col, this.width, col);
      }

      for (Vehicle vehicle : vehicles) {
         vehicle.draw(graphics, this);
      }
   }

   public void addVehicle(Vehicle vehicle) {
      this.vehicles.add(vehicle);
   }

   public Dimension getMaximumSize() {
      return getPreferredSize();
   }

   public Dimension getMinimumSize() {
      return getPreferredSize();
   }

   public void setPreferredSize(Dimension dimension) {
      this.width = dimension.width;
      this.height = dimension.height;
   }

   public Dimension getPreferredSize() {
      return new Dimension(width, height);
   }

   public void mouseClicked(MouseEvent mouse) {
      for (Vehicle vehicle : vehicles) {
         if (mouse.getButton() == MouseEvent.BUTTON1 && vehicle.isMouseOver(mouse.getX(), mouse.getY())) {
            vehicle.setSelected(true);
         } else if (mouse.getButton() == MouseEvent.BUTTON3 && vehicle.isMouseOver(mouse.getX(), mouse.getY())) {
            vehicle.setSelected(false);
         }
      }

      this.repaint();
   }

   public void mouseEntered(MouseEvent mouse) {

   }

   public void mouseExited(MouseEvent mouse) {

   }

   public void mousePressed(MouseEvent mouse) {

   }

   public void mouseReleased(MouseEvent mouse) {

   }

   public void actionPerformed(ActionEvent action) {

   }
}