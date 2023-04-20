import java.util.Scanner;

public class SimulatorDemo {
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Simulator simulator = new Simulator();
    Vehicle vehicle = new Vehicle();

    vehicle.draw();
    vehicle.move();
    vehicle.draw();

    Tire tire = new Tire();

    System.out.println(tire);

    menu(simulator);
  }

  public static void menu(Simulator simulator) {
    boolean isRunning = true;
    int option = -1;

    while(isRunning) {
      option = scanner.nextInt();

      switch(option) {
        case 1:
          break;
        case 2:
          break;
        case 3:
          break;
        case 4:
          break;
        case 5:
          break;
        case 6:
          break;
        case 7:
          break;
        case 8:
          break;
        case 9:
          break;
        case 10:
          break;
        case 11:
          break;
        case 12:
          break;
        default:
          isRunning = false;
          break;
      }
    }
  }
}
