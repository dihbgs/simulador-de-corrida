import java.util.ArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.SuppressWarnings;

public class Simulator {
  private ArrayList<Vehicle> vehicles;

  public Simulator() {
    this.vehicles = new ArrayList<Vehicle>();
  }

  public void addVehicle(Vehicle vehicle) {
    this.vehicles.add(vehicle);
  }

  public void removeVehicle(int id) {
    Vehicle vehicle = this.getVehicleById(id);

    if (vehicle == null) {
      return;
    }

    this.vehicles.remove(vehicle);
  }

  public void refuelVehicle(int id, float fuel) {
    Vehicle vehicle = this.getVehicleById(id);

    if (vehicle == null) {
      return;
    }

    vehicle.refuel(fuel);
  }

  public void moveVehicle(int id) {
    Vehicle vehicle = this.getVehicleById(id);

    if (vehicle == null) {
      return;
    }

    vehicle.move();
  }

  public void moveAllVehicles() {
    for (Vehicle vehicle : this.vehicles) {
      vehicle.move();
    }
  }

  public void printVehicle(int id, boolean printHeader) {
    Vehicle vehicle = this.getVehicleById(id);

    if (vehicle == null) {
      return;
    }

    printHeader();
    printData(vehicle, 1);
    System.out.println();
  }

  public void printAllVehicles() {
    printHeader();
    for (Vehicle vehicle : this.vehicles) {
      printData(vehicle, vehicles.indexOf(vehicle));
    }
    System.out.println();
  }

  private void printData(Vehicle vehicle, int order) {
    System.out
        .printf("   %d\t| #%d\t| \t%.2f\t| %s", order, vehicle.getId(), vehicle.getFuel(), (vehicle.getIpva() ? "Sim" : "Nao"));

    for (int i = 0; i < 4; i++) {
      System.out.print("\t| " + (vehicle.getTire(i).getCalibration() ? "Calibrado" : "Vazio\t"));
    }

    System.out.println();
  }

  private void printHeader() {
    System.out.println(" Nº\t| ID\t| Combustivel\t| IPVA\t| Pneu #1\t| Pneu #2\t| Pneu #3\t| Pneu #4");
  }

  public void printIds() {
    System.out.print("Lista de todos os IDs registrados: \n[");
    for(int i=0; i<vehicles.size(); i++) {
      System.out.print(" #" + vehicles.get(i).getId());
    }
    System.out.println(" ]");
  }

  public boolean validateId(int id) {
    return getVehicleById(id) != null;
  }

  public void emptyTire(int vehicleId, int tireId) {
    Vehicle vehicle = this.getVehicleById(vehicleId);

    if (vehicle == null) {
      return;
    }

    vehicle.emptyTire(tireId);
  }

  public void emptyAllTires(int vehicleId) {
    Vehicle vehicle = this.getVehicleById(vehicleId);

    if (vehicle == null) {
      return;
    }

    vehicle.emptyAllTires();
  }

  public void calibrateTire(int vehicleId, int tireId) {
    Vehicle vehicle = this.getVehicleById(vehicleId);

    if (vehicle == null) {
      return;
    }

    vehicle.calibrateTire(tireId);
  }

  public void calibrateAllTires(int vehicleId) {
    Vehicle vehicle = this.getVehicleById(vehicleId);

    if (vehicle == null) {
      return;
    }

    vehicle.calibrateAllTires();
  }

  public void drawTrack() {
    for (Vehicle vehicle : this.vehicles) {
      vehicle.draw();
    }
  }

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

  @SuppressWarnings("unchecked")
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

  public ArrayList<Vehicle> getVehicles() {
    return this.vehicles;
  }

  private Vehicle getVehicleById(int id) {
    for (Vehicle vehicle : this.vehicles) {
      if (vehicle.getId() == id) {
        return vehicle;
      }
    }

    return null;
  }
}
