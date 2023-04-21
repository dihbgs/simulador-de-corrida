/**
 * Classe que representa uma roda de um carro.
 * 
 * @author Diogo Borges Rodrigues
 */

public class Tire {
  private boolean isCalibrated;

  /*
   * Esse metodo e utilizado para criar uma nova roda.
   */
  public Tire() {
    this.isCalibrated = (Math.random() > 0.5);
  }

  /**
   * Esse metodo e utilizado para setar o estado da roda.
   * 
   * @param boolean isCalibrated - Estado da roda.
   * 
   * @return void
   */
  public void setCalibration(boolean isCalibrated) {
    this.isCalibrated = isCalibrated;
  }

  /**
   * Esse metodo e utilizado para retornar o estado da roda.
   * 
   * @param void
   * 
   * @return boolean - Estado da roda.
   */
  public boolean getCalibration() {
    return this.isCalibrated;
  }

  /**
   * Esse metodo e utilizado para imprimir o estado da roda.
   * 
   * @param void
   * 
   * @return String - Estado da roda.
   * 
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return "Roda " + (isCalibrated ? "calibrada." : "não calibrada.");
  }
}
