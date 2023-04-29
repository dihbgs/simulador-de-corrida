import java.io.Serializable;

/**
 * Classe que representa um veiculo.
 *
 * @author Diogo Borges Rodrigues
 */

public class Vehicle implements Serializable {
  private static final float FUEL_COST = 0.55f;
  private static final String[] MODEL_ONE = {
      "    ____\n",
      " __/  |_ \\_\n",
      "|  _     _``-.\n",
      "'-(_)---(_)--'\n\n"
  };

  private static final String[] MODEL_TWO = {
      "  ______\n",
      " /|_||_\\`.__\n",
      "(   _    _ _\\ \n",
      "=`-(_)--(_)-'\n\n"
  };

  private final String[] MODEL;
  private final Tire[] TIRES;
  private final int ID;

  private float fuel;
  private int distance;
  private boolean hasIPVA;

  /**
   * Esse metodo e utilizado para criar um novo veiculo.
   */
  public Vehicle() {
    this.fuel = 2.5f;
    this.distance = 0;
    this.TIRES = new Tire[4];
    this.hasIPVA = (Math.random() > 0.5);
    this.MODEL = (Math.random() > 0.5) ? Vehicle.MODEL_ONE : Vehicle.MODEL_TWO;

    for (int i = 0; i < 4; i++) {
      this.TIRES[i] = new Tire();
    }

    this.ID = this.generateHash();
  }

  /**
   * Esse metodo e utilizado para obter o ID do veiculo.
   *
   * @param void
   *
   * @return int - ID do veiculo.
   */
  public int getId() {
    return this.ID;
  }

  /**
   * Esse metodo e utilizado para obter a distancia percorrida pelo veiculo.
   *
   * @param void
   *
   * @return int - Distancia percorrida pelo veiculo.
   */
  public int getDistance() {
    return this.distance;
  }

  /**
   * Esse metodo e utilizado para obter o combustivel restante do veiculo.
   *
   * @param void
   *
   * @return float - Combustivel restante do veiculo.
   */
  public float getFuel() {
    return this.fuel;
  }

  /**
   * Esse metodo e utilizado para obter a situacao do IPVA do veiculo.
   *
   * @param void
   *
   * @return boolean - Veiculo possui IPVA pago.
   */
  public boolean getIpva() {
    return this.hasIPVA;
  }

  /**
   * Esse metodo e utilizado para obter as rodas do veiculo.
   *
   * @return Tire[] - Rodas do veiculo.
   *
   * @param void
   */
  public Tire[] getTires() {
    return this.TIRES;
  }

  /**
   * Esse metodo e utilizado para obter uma roda especifica do veiculo.
   *
   * @param int - ID da roda a ser obtida (0, 1, 2 ou 3).
   *
   * @return Tire - Roda especifica do veiculo.
   */
  public Tire getTire(int tireId) {
    if (tireId < 0 || tireId > 3) {
      System.out.println("ID do pneu invalido!");
      return null;
    }

    return this.TIRES[tireId];
  }

  /**
   * Esse metodo e utilizado para reabastecer o veiculo.
   *
   * @param float - Quantidade de combustivel a ser adicionada.
   *
   * @return void
   */
  public void refuel(float fuel) {
    if (fuel < 0) {
      System.out.println("Quantidade de combustivel invalida!");
      return;
    }

    this.fuel += fuel;
    System.out.println("Veiculo #" + this.ID + " abastecido com " + fuel + " litros de combustivel.");
  }

  /**
   * Esse metodo e utilizado para calibrar todos os pneus do veiculo.
   *
   * @param tireId - ID do pneu a ser calibrado (0, 1, 2 ou 3).
   *
   * @return void
   */
  public void calibrateTire(int tireId) {
    if (tireId < 0 || tireId > 3) {
      System.out.println("ID do pneu invalido!");
      return;
    }

    this.TIRES[tireId].setCalibration(true);
    System.out.println("Pneu #" + (tireId + 1) + " calibrado!");
  }

  /**
   * Esse metodo e utilizado para calibrar todos os pneus do veiculo.
   *
   * @param void
   *
   * @return void
   */
  public void calibrateAllTires() {
    for (int i = 0; i < 4; i++) {
      this.TIRES[i].setCalibration(true);
    }
  }

  /**
   * Esse metodo e utilizado para esvaziar todos os pneus do veiculo.
   *
   * @param void
   *
   * @return void
   */
  public void emptyAllTires() {
    for (int i = 0; i < 4; i++) {
      this.TIRES[i].setCalibration(false);
    }
  }

  /**
   * Esse metodo e utilizado para esvaziar um pneu do veiculo.
   *
   * @param int - ID do pneu a ser esvaziado (0, 1, 2 ou 3).
   *
   * @return void
   */
  public void emptyTire(int tireId) {
    if (tireId < 0 || tireId > 3) {
      System.out.println("ID do pneu invalido!");
      return;
    }

    this.TIRES[tireId].setCalibration(false);
    System.out.println("Pneu #" + (tireId + 1) + " esvaziado!");
  }

  /**
   * Esse metodo e utilizado para desenhar o veiculo.
   *
   * @param void
   *
   * @return void
   */
  public void draw() {
    String blocksMoved = "";

    for (int i = 0; i < distance; i++) {
      blocksMoved += "     ";
    }

    for (int i = 0; i < 4; i++) {
      System.out.print(blocksMoved + this.MODEL[i]);
    }
  }

  /**
   * Esse metodo e utilizado para mover o veiculo.
   *
   * @param void
   *
   * @return void
   */
  public void move() {
    if (!this.canMove()) {
      System.out.println("Veiculo #" + this.ID + " nao pode se mover!");
      return;
    }

    this.fuel -= Vehicle.FUEL_COST;
    this.distance += 1;
    System.out.println("Veiculo #" + this.ID + " se moveu!");
  }

  /**
   * Esse metodo e utilizado para imprimir informacoes sobre o veiculo.
   *
   * @param void
   *
   * @return String - Informacoes sobre o veiculo.
   *
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return "Veículo #" + this.ID + " - " + (this.hasIPVA ? "Com" : "Sem") + " IPVA";
  }

  /**
   * Esse metodo e utilizado para verificar se o veiculo tem combustivel
   * suficiente para se mover
   *
   * @return boolean - Se o veiculo tem combustivel suficiente para se mover.
   */
  private boolean hasEnoughFuel() {
    return this.fuel >= Vehicle.FUEL_COST;
  }

  /**
   * Esse metodo e utilizado para verificar se o veiculo esta com todas as rodas
   * calibradas.
   *
   * @return boolean - Se o veiculo esta com todas as rodas calibradas.
   *
   * @see Tire#getCalibration()
   */
  private boolean isFullyCalibrated() {
    for (int i = 0; i < 4; i++) {
      if (!this.TIRES[i].getCalibration()) {
        return false;
      }
    }

    return true;
  }

  /**
   * Esse metodo e utilizado para verificar se o veiculo pode se mover.
   *
   * @return boolean - Se o veiculo pode se mover.
   */
  private boolean canMove() {
    return this.hasEnoughFuel() && this.isFullyCalibrated() && this.hasIPVA;
  }

  /**
   * Esse metodo e utilizado para gerar o ID do veiculo.
   *
   * @return int - ID do veiculo.
   *
   * @see java.lang.Object#hashCode()
   */
  private int generateHash() {
    int hash = this.hashCode() % 100000;

    for (int i = 0; i < 4; i++) {
      hash += this.TIRES[i].hashCode() % 100000;
      hash = (int)Math.floor(hash / 7);
    }

    hash = Math.abs(hash) % 1000;

    return hash;
  }
}
