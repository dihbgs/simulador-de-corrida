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
      Graphics2D graphics = (Graphics2D) context;
      super.paintComponent(graphics);

      graphics.setColor(new Color(40, 40, 40));
      graphics.fillRect(0,0, width,height);

      graphics.setColor(new Color(100, 100, 100));
      for (int row = 0; row < this.width; row += 32) {
         graphics.drawLine(row, 0, row, this.height);
      }
      for (int col = 0; col < this.height; col += 32) {
         graphics.drawLine(0, col, this.width, col);
      }

      for (Vehicle vehicle : vehicles) {
         vehicle.draw(graphics, this);
      }
   }

   public void addVehicle(Vehicle vehicle) {
      if(this.vehicles.size() >= 19) {
         System.err.println("Limite de 20 veiculos atingido.");
         return;
      }

      this.vehicles.add(vehicle);
   }

   public int[] getAllIds() {
      int[] ids = new int[this.vehicles.size()];

      for(int i=0; i<ids.length; i++) {
         ids[i] = this.vehicles.get(i).getID();
      }

      return ids;
   }

   // =================================================================
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
         switch(mouse.getButton()) {
            case MouseEvent.BUTTON1:
               if(vehicle.isMouseOver(mouse.getX(), mouse.getY())) {
                  vehicle.setSelected(true);
               }
               break;
            case MouseEvent.BUTTON2:
               if(vehicle.isMouseOver(mouse.getX(), mouse.getY())) { 
                  vehicle.setSelected(false);
                  System.out.print(vehicle.isSelected());
               }
               break;
            default:
               break;
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