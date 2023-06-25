/**
 * Classe que representa um carro.
 * 
 * @see Tire
 * @see MotorVehicle
 */
public class Car extends MotorVehicle {
   /**
    * Construtor padrao.
    */
   Car() {
      super();

      this.setTax(IPVA.CAR_TAX);
      this.setModel(MotorVehicle.CAR);
      this.setBaseMovement(MotorVehicle.CAR_BASE_MOVEMENT);
      this.setFuelConsumption(MotorVehicle.CAR_FUEL_CONSUMPTION);
   }

   /**
    * Reimplementacao do metodo da classe pai.
    */
   @Override
   public void reloadImage() {
      this.setModel(MotorVehicle.CAR);
   }

   /**
    * Reimplementacao do metodo da classe pai.
    */
   @Override
   public String toString() {
      return "Carro de Passeio #" + this.getID();
   }
}
