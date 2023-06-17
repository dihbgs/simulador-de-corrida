import java.util.Scanner;

/**
 * Classe utilizada para testar o simulador.
 * 
 * @see Vehicle
 * @see Simulator
 */

public class SimulatorDemo {
  private static Scanner scanner = new Scanner(System.in);

  private static Simulator simulator = new Simulator();

  /**
   * Esse metodo e utilizado para iniciar o simulador.
   * 
   * @param String[] - Argumentos passados via linha de comando.
   * 
   * @return void
   * 
   * @see Vehicle
   * @see Simulator
   */
  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    simulator = new Simulator();

    menu();
    scanner.close();
  }

  /**
   * Esse metodo e utilizado para imprimir as opcoes do menu.
   * 
   * @return void
   * 
   * @see Vehicle
   * @see Simulator
   */
  public static void menu() {
    boolean isRunning = true;
    int option = -1;

    while (isRunning) {
      printOptions();

      option = getInputRange(1, 13);

      switch (option) {
        case 1:
          addVehicle();
          break;
        case 2:
          removeVehicle();
          break;
        case 3:
          refuelVehicle();
          break;
        case 4:
          moveVehicle();
          break;
        case 5:
          moveAllVehicles();
          break;
        case 6:
          printVehicle();
          break;
        case 7:
          printAllVehicles();
          break;
        case 8:
          calibrateTire();
          break;
        case 9:
          calibrateAllTires();
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
        case -2:
          // debug(simulator);
          break;
        default:
          System.out.println("Saindo...");
          isRunning = false;
          break;
      }
    }
  }

  /**
   * Move todos os veiculos.
   */
  private static void moveAllVehicles() {
    if (isEmpty()) {
      System.out.println("Nenhum veiculo disponivel.\n");
      return;
    }

    System.out.println("Movendo todos os veiculos.");
    simulator.moveAllVehicles();
    System.out.println();
  }

  /**
   * Calibrar todos os pneus de um veiculo.
   */
  private static void calibrateAllTires() {
    int option, vehicleId;

    if (isEmpty()) {
      System.out.println("Nenhum veiculo disponivel.\n");
      return;
    }

    System.out.println("Esvaziando/calibrando todos os pneus de um veiculo.");
    System.out.print("(1) - Esvaziar pneu. \n(2) - Calibrar pneu. \n(3) - Cancelar. \nDigite a opcao desejada: ");
    option = getInputRange(1, 3);

    if (option == 3) {
      System.out.println();
      return;
    }

    simulator.printIds();
    System.out.print("\nDigite o id do veiculo: ");
    vehicleId = getValidId();

    if (option == 1) {
      simulator.emptyAllTires(vehicleId);
      return;
    }

    if (option == 2) {
      simulator.calibrateAllTires(vehicleId);
      return;
    }

    System.out.println();
  }

  /**
   * Calibra um pneu de um veiculo.
   */
  private static void calibrateTire() {
    int option, vehicleId;

    if (isEmpty()) {
      System.out.println("Nenhum veiculo disponivel.\n");
      return;
    }

    System.out.println("Esvaziando/calibrando um pneu de um veiculo.");
    System.out.print("(1) - Esvaziar pneu. \n(2) - Calibrar pneu. \n(3) - Cancelar. \nDigite a opcao desejada: ");
    option = getInputRange(1, 3);

    if (option == 3) {
      System.out.println();
      return;
    }

    simulator.printIds();
    System.out.print("\nDigite o id do veiculo: ");
    vehicleId = getValidId();

    if (option == 1) {
      System.out.print("\nDigite o pneu que deseja esvaziar: ");
      simulator.emptyTire(vehicleId, getInputRange(1, 4) - 1);
      return;
    }

    if (option == 2) {
      System.out.print("\nDigite o pneu que deseja calibrar: ");
      simulator.calibrateTire(vehicleId, getInputRange(1, 4) - 1);
      return;
    }

    System.out.println();
  }

  /**
   * Imprime dados de todos os veiculos.
   */
  private static void printAllVehicles() {
    if (isEmpty()) {
      System.out.println("Nenhum veiculo disponivel.\n");
      return;
    }

    System.out.println("Imprimindo dados de todos os veiculos.");
    simulator.printAllVehicles();
    System.out.println();
  }

  /**
   * Imprime as informacoes de um veiculo.
   */
  private static void printVehicle() {
    if (isEmpty()) {
      System.out.println("Nenhum veiculo disponivel.\n");
      return;
    }

    System.out.println("Imprimindo dados de um veiculo.");
    simulator.printIds();
    System.out.print("Digite o id do veiculo que deseja imprimir: ");
    simulator.printVehicle(getInput());
    System.out.println();
  }

  /**
   * Move um veiculo.
   */
  private static void moveVehicle() {
    if (isEmpty()) {
      System.out.println("Nenhum veiculo disponivel.\n");
      return;
    }

    System.out.println("Movendo um veiculo.");
    simulator.printAllVehicles();
    System.out.print("Digite o id do veiculo que deseja mover: ");
    simulator.moveVehicle(getInput());
    System.out.println();
  }

  /**
   * Reabastece um veiculo.
   */
  private static void refuelVehicle() {
    if (isEmpty()) {
      System.out.println("Nenhum veiculo disponivel.\n");
      return;
    }

    System.out.println("Abastecendo um veiculo.");
    simulator.printAllVehicles();
    System.out.print("Digite o id do veiculo que deseja abastecer: ");
    int id = getValidId();
    System.out.print("\nDigite a quantidade de combustivel que deseja abastecer: ");
    float fuel = getInput();
    simulator.refuelVehicle(id, fuel);
    System.out.println("\nVeiculo abastecido.\n");
  }

  /**
   * Remove um veiculo do simulador.
   */
  private static void removeVehicle() {
    if (isEmpty()) {
      System.out.println("Nenhum veiculo disponivel.\n");
      return;
    }

    System.out.println("Removendo um veiculo.");
    simulator.printIds();
    System.out.println("Digite o id do veiculo que deseja remover: ");
    simulator.removeVehicle(getValidId());
    System.out.println("Veiculo removido.\n");
  }

  /**
   * Adiciona um veiculo ao simulador.
   */
  private static void addVehicle() {
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

  /**
   * Recebe um valor de entrada e verifica se ele e valido.
   * 
   * @return int - valor de entrada
   * 
   * @throws Exception caso o valor de entrada nao seja um inteiro
   * 
   * @see Exception
   * @see Scanner
   */
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

  /**
   * Recebe um id de veiculo e verifica se ele e valido.
   * 
   * @param
   * 
   * @return int - id do veiculo
   */
  private static int getValidId() {
    int id = getInput();
    boolean isValid = simulator.validateId(id);

    while (!isValid) {
      simulator.printIds();
      id = getInput();
      isValid = simulator.validateId(id);
    }

    return id;
  }

  /**
   * Recebe um valor de entrada e verifica se esta dentro do intervalo
   * especificado.
   * 
   * @param int start
   * @param int end
   * 
   * @return int - valor de entrada
   * 
   * @throws Exception caso o valor de entrada nao esteja dentro do intervalo
   *                   especificado.
   */
  private static int getInputRange(int start, int end) {
    int input = getInput();

    while (input < start || input > end) {
      System.out.print("O valor inserido deve estar entre (" + start + " ~ " + end + "): ");
      input = getInput();
    }

    return input;
  }

  /**
   * Verifica se o simulador esta vazio.
   * 
   * @param
   * 
   * @return boolean - true se estiver vazio, false caso contrario.
   */
  private static boolean isEmpty() {
    return simulator.getVehicles().isEmpty();
  }

  /**
   * Imprime as opcoes do menu.
   * 
   * @return void
   */
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
    System.out.println("13 - Para sair do menu.\n");
    System.out.print("Digite a opcao desejada: ");
  }
}
