import javax.swing.JOptionPane;
import javax.swing.JComponent;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Classe que repesenta a pista de veiculos de um simulador.
 * 
 * @see Vehicle
 * @see MotorVehicle
 */
public class Road extends JComponent {
   public static final int MOTORCYCLE = 4;
   public static final int SPORTCAR = 3;
   public static final int BIKE = 2;
   public static final int CAR = 1;

   private ArrayList<Vehicle> vehicles;
   private int width;
   private int height;

   /**
    * Construtor padrão.
    */
   public Road() {
      this.width = 100;
      this.height = 100;
      this.vehicles = new ArrayList<Vehicle>();
   }

   /**
    * Metodo que adiciona um veiculo a pista.
    * 
    * @param vehicle - veiculo a ser adicionado.
    */
   public void addVehicle(Vehicle vehicle) {
      if (this.vehicles.size() > 19) {
         JOptionPane.showMessageDialog(null, "Limite de 20 veiculos atingido.");
         return;
      }

      vehicle.setY(vehicles.size());
      this.vehicles.add(vehicle);
   }

   /**
    * Metodo que remove um veiculo da pista pelo seu ID.
    * 
    * @param id - ID do veiculo a ser removido.
    */
   public void removeVehicle(int id) {
      for (Vehicle vehicle : vehicles) {
         if (vehicle.getID() == id) {
            this.vehicles.remove(vehicle);
            JOptionPane.showMessageDialog(null, "Veiculo removido.");
            updateVehicles();
            return;
         }
      }

      JOptionPane.showMessageDialog(null, "Nenhum veiculo removido.");
   }

   /**
    * Metodo que retorna todos os veiculos da pista.
    * 
    * @return Vehicle[] - veiculos da pista.
    */
   public Vehicle[] getVehicles() {
      return this.vehicles.toArray(new Vehicle[this.vehicles.size()]);
   }

   /**
    * Metodo que registra varios veiculos de uma vez.
    * 
    * @param vehicles - veiculos a serem registrados.
    */
   public void setVehicles(Vehicle[] vehicles) {
      while (!this.vehicles.isEmpty()) {
         this.vehicles.remove(0);
      }

      for (Vehicle vehicle : vehicles) {
         this.addVehicle(vehicle);
         vehicle.reloadImage();
      }
   }

   /**
    * Metodo que retorna todos os veiculos de um mesmo tipo.
    * 
    * @param type - tipo dos veiculos desejados.
    * @return Vehicle[] - veiculos do tipo desejado.
    */
   public Vehicle[] getByType(int type) {
      ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

      for (Vehicle vehicle : this.vehicles) {
         if (this.getType(vehicle) == type) {
            vehicles.add(vehicle);
         }
      }

      return vehicles.toArray(new Vehicle[vehicles.size()]);
   }

   /**
    * Metodo que retorna uma lista do ID de todos os veiculos.
    * 
    * @return int[] - lista de todos os IDs.
    */
   public int[] getAllIds() {
      int[] ids = new int[this.vehicles.size()];

      for (int i = 0; i < ids.length; i++) {
         ids[i] = this.vehicles.get(i).getID();
      }

      return ids;
   }

   /**
    * Metodo que retorna se a pista esta vazia.
    * 
    * @return boolean - se a pista esta vazia.
    */
   public boolean isEmpty() {
      return this.vehicles.isEmpty();
   }

   /**
    * Metodo que retorna se a pista esta cheia.
    * 
    * @return boolean - se a pista esta cheia.
    */
   public boolean isFull() {
      return this.vehicles.size() >= 20;
   }

   /**
    * Metodo que retorna um veiculo pelo seu ID.
    * 
    * @param id - ID do veiculo desejado.
    * @return Vehicle - veiculo com ID desejado.
    */
   public Vehicle getById(int id) {
      for (Vehicle vehicle : vehicles) {
         if (vehicle.getID() == id) {
            return vehicle;
         }
      }

      return null;
   }

   /**
    * Metodo privado que retorna o tipo do veiculo especificado.
    * 
    * @param vehicle - veiculo para checagem.
    * @return int - tipo do veiculo especificado.
    */
   private int getType(Vehicle vehicle) {
      if (vehicle instanceof Car) {
         return CAR;
      }

      if (vehicle instanceof Motorcycle) {
         return MOTORCYCLE;
      }

      if (vehicle instanceof Bike) {
         return BIKE;
      }

      if (vehicle instanceof Sportcar) {
         return SPORTCAR;
      }

      return -1;
   }

   /**
    * Metodo privado que atualiza a posicao vertical de todos os veiculos na pista.
    */
   private void updateVehicles() {
      for (int i = 0; i < vehicles.size(); i++) {
         vehicles.get(i).setY(i);
      }
   }

   // =================================================================

   /**
    * Metodo que desenha o componente pista e todos os veiculos.
    */
   public void paintComponent(Graphics context) {
      Color rulerColor = new Color(255, 255, 255);
      Color lineColor = new Color(230, 230, 230);
      Color roadColor = new Color(80, 80, 80);
      Color fillColor = new Color(230, 230, 0);

      Graphics2D graphics = (Graphics2D) context;
      super.paintComponent(graphics);
      int maxDistance = 0;

      graphics.setColor(roadColor);
      graphics.fillRect(0, 0, width, height);

      for (int row = 0; row < vehicles.size(); row++) {
         graphics.setColor(fillColor);
         for (int col = 16; col < width; col += 64) {
            graphics.fillRect(col, 48 + 64 * row, 32, 4);
         }
         graphics.setColor(lineColor);
         graphics.fillRect(0, row * 64 + 16, width, 4);
      }

      graphics.fillRect(0, vehicles.size() * 64 + 16, width, 4);

      graphics.setColor(rulerColor);
      for (int row = 64; row <= width; row += 32) {
         if (row % 352 == 0) {
            graphics.fillRect(row, 0, 1, 8);
         } else {
            graphics.fillRect(row, 0, 1, 4);
         }
      }

      for (Vehicle vehicle : vehicles) {
         if (vehicle.getFullMovement() > maxDistance) {
            maxDistance = vehicle.getFullMovement();
         }
         vehicle.draw(graphics, this);
      }

      this.setPreferredSize(new Dimension(1024 + maxDistance * 16, this.getHeight()));
   }

   /**
    * Metodo que retorna a dimensao maxima da pista.
    * 
    * @return Dimension - dimensao maxima.
    */
   public Dimension getMaximumSize() {
      return getPreferredSize();
   }

   /**
    * Metodo que retorna a dimensao minima da pista.
    * 
    * @return Dimension - dimensao minima.
    */
   public Dimension getMinimumSize() {
      return getPreferredSize();
   }

   /**
    * Metodo que altera a dimensao ideal da pista.
    * 
    * @param dimension - dimensao ideal.
    */
   public void setPreferredSize(Dimension dimension) {
      this.width = dimension.width;
      this.height = dimension.height;
   }

   /**
    * Metodo que retorna a dimensao ideal da pista.
    * 
    * @return Dimension - dimensao ideal.
    */
   public Dimension getPreferredSize() {
      return new Dimension(width, height);
   }
}