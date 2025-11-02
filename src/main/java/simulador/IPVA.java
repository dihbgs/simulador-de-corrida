/**
 * Interface que representa o IPVA de um veiculo.
 * 
 * @see MotorVehicle
 */
public interface IPVA {
   public static final float CAR_TAX = 1.30f;
   public static final float BASE_COST = 500f;
   public static final float SPORTCAR_TAX = 3.15f;
   public static final float MOTORCYCLE_TAX = 0.75f;

   /**
    * Metodo para calcular o valor do IPVA do veiculo.
    * 
    * @return float - valor do IPVA.
    * 
    * @see MotorVehicle
    */
   public float calculateIPVA();

   /**
    * Metodo para checar se o IPVA do veiculo esta pago.
    * 
    * @return boolean - se o IPVA esta pago.
    * 
    * @see MotorVehicle
    */
   public boolean hasPaidIPVA();
}
