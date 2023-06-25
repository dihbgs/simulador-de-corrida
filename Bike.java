/**
 * Classe que representa uma bicicleta.
 * 
 * @see Tire
 * @see Vehicle
 */
public class Bike extends Vehicle {
   /**
    * Construtor padrao.
    */
   Bike() {
      super(BIKE);
      this.setNumberOfTires(2);
      initiateTires();
   }

   /**
    * Reimplementacao do metodo da classe pai.
    */
   @Override
   public String toString() {
      return "Bicicleta #" + this.getID();
   }
}
