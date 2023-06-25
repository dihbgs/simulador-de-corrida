/**
 * Classe que representa um carro esportivo.
 * 
 * @see Tire
 * @see MotorVehicle
 */
public class Sportcar extends MotorVehicle {
   /**
    * Construtor padrao
    */
   Sportcar() {
      super(SPORTCAR);

      this.setTax(IPVA.SPORTCAR_TAX);
      this.setBaseMovement(MotorVehicle.SPORTCAR_BASE_MOVEMENT);
      this.setFuelConsumption(MotorVehicle.SPORTCAR_FUEL_CONSUMPTION);
   }

   /**
    * Reimplementacao do metodo da classe pai.
    */
   @Override
   public void reloadImage() {
      this.setModel(MotorVehicle.SPORTCAR);
   }

   /**
    * Reimplementacao do metodo da classe pai.
    */
   @Override
   public String toString() {
      return "Carro Esportivo #" + this.getID();
   }
}
