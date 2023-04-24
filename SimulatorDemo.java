import java.util.Scanner;

public class SimulatorDemo {
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Simulator simulator = new Simulator();

    menu(simulator);
    scanner.close();
  }

  public static void menu(Simulator simulator) {
    boolean isRunning = true;
    int option = -1;

    while (isRunning) {
      printOptions();

      option = getInput();

      switch (option) {
        case 1:
          addVehicle(simulator);
          break;
        case 2:
          removeVehicle(simulator);
          break;
        case 3:
          refuelVehicle(simulator);
          break;
        case 4:
          moveVehicle(simulator);
          break;
        case 5:
          System.out.println("Movendo todos os veiculos.");
          simulator.moveAllVehicles();
          break;
        case 6:
          printVehicle(simulator);
          break;
        case 7:
          printAllVehicles(simulator);
          break;
        case 8:
          System.out.println("Esvaziando/calibrando um pneu de um veiculo.");
          break;
        case 9:
          System.out.println("Esvaziando/calibrando todos os pneus de um veiculo.");
          break;
        case 10:
          System.out.println("Desenhando pista de corrida.");
          simulator.drawTrack();
          break;
        case 11:
          System.out.println("Salvando dados dos veiculos em um arquivo.");
          simulator.saveData();
          System.out.println("Dados salvos.\n");
          break;
        case 12:
          System.out.println("Carregando dados dos veiculos de um arquivo.");
          simulator.loadData();
          System.out.println("Dados carregados.\n");
          break;
        default:
          System.out.println("Saindo...");
          isRunning = false;
          break;
      }
    }
  }

  private static void printAllVehicles(Simulator simulator) {
    System.out.println("Imprimindo dados de todos os veiculos.");
    simulator.printAllVehicles();
    System.out.println();
  }

  private static void printVehicle(Simulator simulator) {
    System.out.println("Imprimindo dados de um veiculo.");
    System.out.println("Digite o id do veiculo que deseja imprimir: ");
    simulator.printVehicle(getInput(), true);
    System.out.println();
  }

  private static void moveVehicle(Simulator simulator) {
    System.out.println("Movendo um veiculo.");
    simulator.printAllVehicles();
    System.out.println("Digite o id do veiculo que deseja mover: ");
    simulator.moveVehicle(getInput());
    System.out.println("Veiculo movido.\n");
  }

  private static void refuelVehicle(Simulator simulator) {
    System.out.println("Abastecendo um veiculo.");
    simulator.printAllVehicles();
    System.out.println("Digite o id do veiculo que deseja abastecer: ");
    int id = getInput();
    System.out.println("Digite a quantidade de combustivel que deseja abastecer: ");
    float fuel = getInput();
    simulator.refuelVehicle(id, fuel);
    System.out.println("Veiculo abastecido.\n");
  }

  private static void removeVehicle(Simulator simulator) {
    System.out.println("Removendo um veiculo.");
    simulator.printAllVehicles();
    System.out.println("Digite o id do veiculo que deseja remover: ");
    simulator.removeVehicle(getInput());
    System.out.println("Veiculo removido.\n");
  }

  private static void addVehicle(Simulator simulator) {
    if (simulator.getVehicles().size() >= 20) {
      System.out.println("Nao e possivel adicionar mais veiculos.\n");
      return;
    }

    Vehicle vehicle = new Vehicle();
    System.out.println("Adicionando um veiculo.");
    simulator.addVehicle(vehicle);
    System.out.println("Veiculo #" + vehicle.getId() + " adicionado.");
    System.out.println(simulator.getVehicles().size() + " veiculos no total.\n");
  }

  private static int getInput() {
    int option = -1;

    while (option == -1) {
      try {
        option = scanner.nextInt();
      } catch (Exception e) {
        System.out.println("Opcao invalida.");
        scanner.nextLine();
      }
    }

    return option;
  }

  private static void printOptions() {
    System.out.println("==========\tMenu de Opcoes\t==========");
    System.out.println(" 1 - Adicionar um veiculo.");
    System.out.println(" 2 - Remover um veiculo.");
    System.out.println(" 3 - Abastecer um veiculo.");
    System.out.println(" 4 - Mover um veiculo.");
    System.out.println(" 5 - Mover todos os veiculos.");
    System.out.println(" 6 - Imprimir dados de um veiculo.");
    System.out.println(" 7 - Imprimir dados de todos os veiculos.");
    System.out.println(" 8 - Esvaziar/calibrar um pneu de um veiculo.");
    System.out.println(" 9 - Esvaziar/calibrar todos os pneus de um veiculo.");
    System.out.println("10 - Imprimir pista de corrida.");
    System.out.println("11 - Salvar dados dos veiculos em um arquivo.");
    System.out.println("12 - Carregar dados dos veiculos de um arquivo.");
    System.out.println("Digite qualquer outro numero para sair.\n");
    System.out.print("Digite a opcao desejada: ");
  }
}
