import java.awt.Image;

/**
 * Classe abstrata que representa um veiculo motorizado
 * 
 * @see IPVA
 * @see Tire
 * @see Vehicle
 */
public abstract class MotorVehicle extends Vehicle implements IPVA {
   public static Image MOTORCYCLE = Vehicle.loadImage("MOTORCYCLE.png");
   public static Image SPORTCAR = Vehicle.loadImage("SPORTCAR.png");
   public static Image CAR = Vehicle.loadImage("CAR.png");

   public static final float MOTORCYCLE_FUEL_CONSUMPTION = 0.25f;
   public static final float SPORTCAR_FUEL_CONSUMPTION = 2.30f;
   public static final float CAR_FUEL_CONSUMPTION = 0.75f;

   public static final int MOTORCYCLE_BASE_MOVEMENT = 3;
   public static final int SPORTCAR_BASE_MOVEMENT = 10;
   public static final int CAR_BASE_MOVEMENT = 5;

   private float fuelConsumption;
   private boolean hasPaidIPVA;
   private float fuel;
   private float tax;

   /**
    * Construtor padrao.
    */
   public MotorVehicle() {
      this(null);
   }

   /**
    * Construtor parametrizado.
    * 
    * @param model - modelo do veiculo.
    */
   public MotorVehicle(Image model) {
      super(model);
      this.tax = 0;
      this.fuel = 2.5f;
      this.fuelConsumption = 0;
      this.hasPaidIPVA = (Math.random() > 0.5);
      this.setNumberOfTires(4);
      this.initiateTires();

   }

   /**
    * Metodo que move o veiculo.
    * 
    * @return boolean - se o veiculo se moveu com sucesso.
    */
   public boolean move() {
      if (!(hasEnoughFuel() && areTiresCalibrated() && hasPaidIPVA())) {
         return false;
      }

      this.setX(this.getX() + getBaseMovement());
      this.fuel -= fuelConsumption;

      return true;
   }

   /**
    * Metodo que reabastece o veiculo.
    * 
    * @param fuelAmount - quantidade de combustivel.
    */
   public void refuel(float fuelAmount) {
      this.fuel += fuelAmount;
   }

   /**
    * Metodo que retorna o valor do IPVA
    * 
    * @return float - valor do IPVA.
    * @see IPVA
    */
   public float calculateIPVA() {
      return IPVA.BASE_COST * this.tax;
   }

   /**
    * Metodo que retorna a condicao do IPVA.
    * 
    * @return boolean - se o IPVA esta pago.
    * @see IPVA
    */
   public boolean hasPaidIPVA() {
      return this.hasPaidIPVA;
   }

   /**
    * Metodo que retorna a quantidade de combustivel.
    * 
    * @return float - quantidade de combustivel.
    */
   public float getFuel() {
      return this.fuel;
   }

   /**
    * Metodo que registra a aliquota do IPVA.
    * 
    * @param taxAmount - aliquota.
    * @see IPVA
    */
   public void setTax(float taxAmount) {
      this.tax = taxAmount;
   }

   /**
    * Metodo que registra o consumo do veiculo.
    * 
    * @param fuelConsumption - consumo do veiculo.
    */
   public void setFuelConsumption(float fuelConsumption) {
      this.fuelConsumption = fuelConsumption;
   }

   /**
    * Metodo privado que retorna se o veiculo possui combustivel suficiente para se
    * deslocar.
    * 
    * @return boolean - se possui combustivel suficiente.
    */
   private boolean hasEnoughFuel() {
      return (this.fuel - this.fuelConsumption) > 0;
   }
}
