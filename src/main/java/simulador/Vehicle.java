import javax.imageio.ImageIO;
import java.io.Serializable;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

/**
 * Classe abstrata que representa um veiculo.
 * 
 * @see Bike
 * @see Tire
 * @see Serializable
 */
public abstract class Vehicle implements Serializable {
   public static Image BIKE = loadImage("BIKE.png");

   private final int ID;

   private transient Image model;
   private int numberOfTires;
   private int baseMovement;
   private Tire[] tires;
   private int height;
   private int width;
   private int x;
   private int y;

   /**
    * Construtor padrao.
    */
   Vehicle() {
      this(null);
   }

   /**
    * Construtor parametrizado.
    * 
    * @param model - modelo do veiculo.
    */
   Vehicle(Image model) {
      numberOfTires = 2;
      initiateTires();
      this.x = 0;
      this.y = 0;
      this.width = 64;
      this.height = 64;
      this.model = model;
      this.baseMovement = 2;
      this.ID = generateHash();
   }

   /**
    * Metodo estatico utilizado para carregar um arquivo de imagem do computador.
    *
    * @param file - caminho do arquivo.
    * @return Image - objeto de imagem.
    */
   public static Image loadImage(String file) {
      Image image;

      try {
         image = ImageIO.read(new File("src/" + file));
      } catch (IOException e) {
         System.err.println("Falha ao carregar imagem.");
         image = null;
      }

      return image;
   }

   /**
    * Metodo utilizado para mover um veiculo.
    *
    * @return boolean - se o veiculo se moveu com sucesso.
    */
   public boolean move() {
      if (!areTiresCalibrated()) {
         return false;
      }

      this.setX(this.getX() + this.baseMovement);
      return true;
   }

   /**
    * Metodo utilizado para desenhar o veiculo na pista.
    * 
    * @param graphics - objeto contendo o contexto grafico para desenhar na pista.
    * @param road     - pista.
    */
   public void draw(Graphics graphics, Road road) {
      graphics.drawImage(model, (int) Math.floor(0.5 * x * width), y * height, width, height, road);
   }

   /**
    * Metodo que retorna o valor total que um veiculo pode se mover de uma vez.
    * 
    * @return int - quantia total de movimento do veiculo.
    */
   public int getFullMovement() {
      return this.x * this.baseMovement;
   }

   /**
    * Metodo que retorna a posicao horizontal do veiculo.
    * 
    * @return int - posicao horizontal.
    */
   public int getX() {
      return this.x;
   }

   /**
    * Metodo que retorna a posicao vertical do veiculo.
    * 
    * @return int - posicao vertical.
    */
   public int getY() {
      return this.y;
   }

   /**
    * Metodo que retorna o movimento base do veiculo.
    * 
    * @return int - movimento base.
    */
   public int getBaseMovement() {
      return this.baseMovement;
   }

   /**
    * Metodo que retorna a quantia de rodas do veiculo.
    * 
    * @return int - quantia de rodas.
    */
   public int getNumberOfTires() {
      return this.numberOfTires;
   }

   /**
    * Metodo que registra a posicao horizontal do veiculo.
    * 
    * @param x - posicao horizontal.
    */
   public void setX(int x) {
      this.x = x;
   }

   /**
    * Metodo que registra a posicao vertical do veiculo.
    * 
    * @param y - posicao vertical.
    */
   public void setY(int y) {
      this.y = y;
   }

   /**
    * Metodo que registra o modelo do veiculo.
    * 
    * @param model - modelo do veiculo.
    */
   public void setModel(Image model) {
      this.model = model;
   }

   /**
    * Metodo que registra a quantidade de rodas do veiculo.
    * 
    * @param numberOfTires - quantidade de rodas.
    */
   public void setNumberOfTires(int numberOfTires) {
      this.numberOfTires = numberOfTires;
   }

   /**
    * Metodo que registra o movimento base do veiculo.
    * 
    * @param movementAmount - movimento base.
    */
   public void setBaseMovement(int movementAmount) {
      this.baseMovement = movementAmount;
   }

   /**
    * Metodo que recarrega o modelo do veiculo.
    * 
    * @see Image
    */
   public void reloadImage() {
      this.model = Vehicle.BIKE;
   }

   /**
    * Metodo que retorna o ID do veiculo.
    * 
    * @return int - ID do veiculo.
    */
   public int getID() {
      return this.ID;
   }

   /**
    * Metodo que retorna uma array de rodas.
    * 
    * @return Tire[] - rodas.
    * @see Tire
    */
   public Tire[] getTires() {
      return this.tires;
   }

   /**
    * Metodo para criar as rodas do veiculo.
    * 
    * @see Tire
    */
   public void initiateTires() {
      this.tires = new Tire[numberOfTires];

      for (int i = 0; i < numberOfTires; i++) {
         this.tires[i] = new Tire();
      }
   }

   /**
    * Metodo que verifica se todas as rodas estao calibradas.
    * 
    * @return boolean - se todas as rodas estao calibradas.
    * @see Tire
    */
   public boolean areTiresCalibrated() {
      for (int i = 0; i < this.numberOfTires; i++) {
         if (this.tires[i].isCalibrated()) {
            return true;
         }
      }

      return false;
   }

   /**
    * Metodo privado para geracao de ID do veiculo.
    * 
    * @return int - ID do veiculo.
    */
   private int generateHash() {
      int hash = this.hashCode() % 100000;

      for (int i = 0; i < this.numberOfTires; i++) {
         hash += this.tires[i].hashCode() % 100000;
         hash = (int) Math.floor(hash / 7);
      }

      hash = Math.abs(hash) % 1000;

      return hash;
   }
}
