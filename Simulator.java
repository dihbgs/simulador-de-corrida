import java.util.ArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe que representa um simulador de veiculos.
 * 
 * @see Vehicle
 */
public class Simulator {
  private ArrayList<Vehicle> vehicles;

  /**
   * Esse metodo e utilizado para criar um novo simulador.
   * 
   * @param void
   * 
   * @return void
   * 
   * @see Vehicle
   */
  public Simulator() {
    this(0);
  }

  /**
   * Esse metodo e utilizado para criar um novo simulador.
   * 
   * @param int - Quantidade de veiculos a serem criados.
   * 
   * @return void
   * 
   * @see Vehicle
   */
  public Simulator(int vehicles) {
    this.vehicles = new ArrayList<Vehicle>();

    for (int i = 0; i < vehicles; i++) {
      this.vehicles.add(new Vehicle());
    }
  }

  /**
   * Esse metodo e utilizado para adicionar um novo veiculo ao simulador.
   * 
   * @param vehicle - Veiculo a ser adicionado.
   * 
   * @return void
   * 
   * @see Vehicle
   */
  public void addVehicle(Vehicle vehicle) {
    this.vehicles.add(vehicle);
  }

  /**
   * Esse metodo e utilizado para remover um veiculo do simulador.
   * 
   * @param int - ID do veiculo a ser removido.
   * 
   * @return void
   * 
   * @see Vehicle
   */
  public void removeVehicle(int id) {
    Vehicle vehicle = this.getVehicleById(id);

    if (vehicle == null) {
      return;
    }

    this.vehicles.remove(vehicle);
  }

  /**
   * Esse metodo e utilizado para reabastecer um veiculo.
   * 
   * @param int   - ID do veiculo a ser reabastecido.
   * @param float - Quantidade de combustivel a ser abastecido.
   * 
   * @return void
   * 
   * @see Vehicle
   */
  public void refuelVehicle(int id, float fuel) {
    Vehicle vehicle = this.getVehicleById(id);

    if (vehicle == null) {
      return;
    }

    vehicle.refuel(fuel);
  }

  /**
   * Esse metodo e utilizado para mover um veiculo.
   * 
   * @param int - ID do veiculo a ser movido.
   * 
   * @return void
   * 
   * @see Vehicle
   */
  public void moveVehicle(int id) {
    Vehicle vehicle = this.getVehicleById(id);

    if (vehicle == null) {
      return;
    }

    vehicle.move();
  }

  /**
   * Esse metodo e utilizado para mover todos os veiculos.
   * 
   * @return void
   * 
   * @see Vehicle
   */
  public void moveAllVehicles() {
    for (Vehicle vehicle : this.vehicles) {
      vehicle.move();
    }
  }

  /**
   * Esse metodo e utilizado para imprimir os dados de um veiculo.
   * 
   * @param int - ID do veiculo a ser impresso.
   * 
   * @return void
   * 
   * @see Vehicle
   */
  public void printVehicle(int id) {
    Vehicle vehicle = this.getVehicleById(id);

    if (vehicle == null) {
      return;
    }

    printHeader();
    printData(vehicle, 1);
    System.out.println();
  }

  /**
   * Esse metodo e utilizado para imprimir os dados de todos os veiculos.
   * 
   * @return void
   * 
   * @see Vehicle
   */
  public void printAllVehicles() {
    printHeader();
    for (Vehicle vehicle : this.vehicles) {
      printData(vehicle, vehicles.indexOf(vehicle));
    }
    System.out.println();
  }

  /**
   * Esse metodo e utilizado internamente pelo simulador para auxiliar na
   * formatacao dos dados.
   * 
   * @param Vehicle - Veiculo a ser impresso.
   * @param int     - Ordem do veiculo na lista.
   * 
   * @return void
   */
  private void printData(Vehicle vehicle, int order) {
    System.out
        .printf("   %d\t| #%d\t| \t%.2f\t| %s", order, vehicle.getId(), vehicle.getFuel(),
            (vehicle.getIpva() ? "Sim" : "Nao"));

    for (int i = 0; i < 4; i++) {
      System.out.print("\t| " + (vehicle.getTire(i).getCalibration() ? "Calibrado" : "Vazio\t"));
    }

    System.out.println();
  }

  /**
   * Esse metodo e utilizado internamente pelo simulador para auxiliar na
   * formatacao dos dados.
   * 
   * @return void
   */
  private void printHeader() {
    System.out.println(" Nº\t| ID\t| Combustivel\t| IPVA\t| Pneu #1\t| Pneu #2\t| Pneu #3\t| Pneu #4");
  }

  /**
   * Esse metodo e utilizado para imprimir todos os IDs registrados.
   * 
   * @return void
   */
  public void printIds() {
    System.out.print("Lista de todos os IDs registrados: \n[");
    for (int i = 0; i < vehicles.size(); i++) {
      System.out.print(" #" + vehicles.get(i).getId());
    }
    System.out.println(" ]");
  }

  /**
   * Esse metodo e utilizado para verificar se um ID ja esta registrado.
   * 
   * @param int - ID a ser verificado.
   * 
   * @return boolean
   */
  public boolean validateId(int id) {
    return getVehicleById(id) != null;
  }

  /**
   * Esse metodo e utilizado para esvaziar um pneu.
   * 
   * @param int - ID do veiculo.
   * @param int - ID do pneu.
   * 
   * @return void
   * 
   * @see Vehicle
   * @see Tire
   */
  public void emptyTire(int vehicleId, int tireId) {
    Vehicle vehicle = this.getVehicleById(vehicleId);

    if (vehicle == null) {
      return;
    }

    vehicle.emptyTire(tireId);
  }

  /**
   * Esse metodo e utilizado para esvaziar todos os pneus de um veiculo.
   * 
   * @param int - ID do veiculo.
   * 
   * @return void
   * 
   * @see Vehicle
   * @see Tire
   */
  public void emptyAllTires(int vehicleId) {
    Vehicle vehicle = this.getVehicleById(vehicleId);

    if (vehicle == null) {
      return;
    }

    vehicle.emptyAllTires();
  }

  /**
   * Esse metodo e utilizado para calibrar um pneu.
   * 
   * @param int - ID do veiculo.
   * @param int - ID do pneu.
   * 
   * @return void
   * 
   * @see Vehicle
   * @see Tire
   */
  public void calibrateTire(int vehicleId, int tireId) {
    Vehicle vehicle = this.getVehicleById(vehicleId);

    if (vehicle == null) {
      return;
    }

    vehicle.calibrateTire(tireId);
  }

  /**
   * Esse metodo e utilizado para calibrar todos os pneus de um veiculo.
   * 
   * @param int - ID do veiculo.
   * 
   * @return void
   * 
   * @see Vehicle
   * @see Tire
   */
  public void calibrateAllTires(int vehicleId) {
    Vehicle vehicle = this.getVehicleById(vehicleId);

    if (vehicle == null) {
      return;
    }

    vehicle.calibrateAllTires();
  }

  /**
   * Esse metodo e utilizado para desenhar a pista.
   * 
   * @return void
   */
  public void drawTrack() {
    for (Vehicle vehicle : this.vehicles) {
      vehicle.draw();
    }
  }

  /**
   * Esse metodo e utilizado para salvar os dados em um arquivo.
   * 
   * @return void
   */
  public void saveData() {
    File file = new File("vehicles.dat");

    try {
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

      objectOutputStream.writeObject(this.vehicles);

      objectOutputStream.flush();
      objectOutputStream.close();
      fileOutputStream.close();
    } catch (Exception e) {
      System.out.println("Erro ao salvar os dados. \n" + e.getMessage());
    }
  }

  /**
   * Esse metodo e utilizado para carregar os dados de um arquivo.
   * 
   * @return void
   */
  public void loadData() {
    File file = new File("vehicles.dat");

    try {
      FileInputStream fileInputStream = new FileInputStream(file);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

      this.vehicles = (ArrayList<Vehicle>) objectInputStream.readObject();

      objectInputStream.close();
      fileInputStream.close();
    } catch (Exception e) {
      System.out.println("Erro ao carregar os dados. \n" + e.getMessage());
    }
  }

  /**
   * Esse metodo e utilizado para obter a lista de veiculos.
   * 
   * @return ArrayList<Vehicle> - Lista de veiculos.
   * 
   * @see Vehicle
   */
  public ArrayList<Vehicle> getVehicles() {
    return this.vehicles;
  }

  /**
   * Esse metodo e utilizado para obter um veiculo pelo seu ID.
   * 
   * @param int - ID do veiculo.
   * 
   * @return Vehicle
   * 
   * @see Vehicle
   */
  private Vehicle getVehicleById(int id) {
    for (Vehicle vehicle : this.vehicles) {
      if (vehicle.getId() == id) {
        return vehicle;
      }
    }

    return null;
  }
}
