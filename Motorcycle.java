/**
 * Classe que representa uma motocicleta.
 * 
 * @see Tire
 * @see MotorVehicle
 */
public class Motorcycle extends MotorVehicle {
   /**
    * Construtor padrao.
    */
   Motorcycle() {
      super();

      this.setTax(IPVA.MOTORCYCLE_TAX);
      this.setModel(MotorVehicle.MOTORCYCLE);
      this.setBaseMovement(MotorVehicle.MOTORCYCLE_BASE_MOVEMENT);
      this.setFuelConsumption(MotorVehicle.MOTORCYCLE_FUEL_CONSUMPTION);
      this.setNumberOfTires(2);
      initiateTires();
   }

   /**
    * Reimplementacao do metodo da classe pai.
    */
   @Override
   public void reloadImage() {
      this.setModel(MotorVehicle.MOTORCYCLE);
   }

   /**
    * Reimplementacao do metodo da classe pai.
    */
   @Override
   public String toString() {
      return "Motocicleta #" + this.getID();
   }
}
