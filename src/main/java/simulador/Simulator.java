import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.io.File;

/**
 * Classe que representa um simulador de veiculos.
 * 
 * @see Road
 * @see Vehicle
 * @see MotorVehicle
 */
public class Simulator extends JFrame {
    private ArrayList<JButton> buttons;
    private Road road;

    /**
     * Metodo main.
     * 
     * @param args - array de argumentos passados ao programa.
     */
    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        simulator.setVisible(true);
    }

    /**
     * Construtor.
     */
    public Simulator() {
        road = new Road();
        buttons = new ArrayList<JButton>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Simulador");
        setSize(1024, 640);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        road.setPreferredSize(new Dimension(1024, 1280));

        JScrollPane scrollPane = new JScrollPane(road);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(32);

        JPanel leftComponent = new JPanel();
        leftComponent.setLayout(new BorderLayout());
        leftComponent.add(scrollPane, BorderLayout.CENTER);

        JPanel rightComponent = new JPanel();
        rightComponent.setLayout(new GridLayout(0, 1));

        buttons.add(new JButton("Adicionar veiculo"));
        buttons.add(new JButton("Remover veiculo"));
        buttons.add(new JButton("Abastecer veiculo"));
        buttons.add(new JButton("Mover por ID"));
        buttons.add(new JButton("Mover por tipo"));
        buttons.add(new JButton("Mover todos"));
        buttons.add(new JButton("Imprimir todos"));
        buttons.add(new JButton("Imprimir por tipo"));
        buttons.add(new JButton("Esvaziar/Calibrar roda"));
        buttons.add(new JButton("Calibrar rodas por tipo"));
        buttons.add(new JButton("Esvaziar rodas por tipo"));
        buttons.add(new JButton("Salvar em arquivo"));
        buttons.add(new JButton("Carregar de arquivo"));

        addButtonEvents();
        updateButtons();

        for (JButton button : buttons) {
            rightComponent.add(button);
        }

        mainPanel.add(leftComponent, BorderLayout.WEST);
        mainPanel.add(rightComponent, BorderLayout.EAST);

        add(mainPanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(this.getWidth(), 640);
    }

    /**
     * Metodo que retonar a pista.
     * 
     * @return Road - pista.
     * 
     * @see Road
     */
    public Road getRoad() {
        return this.road;
    }

    /**
     * Metodo que adiciona um veiculo a pista.
     */
    public void addVehicle() {
        int type = this.getValidType();

        switch (type) {
            case Road.CAR:
                road.addVehicle(new Car());
                break;
            case Road.BIKE:
                road.addVehicle(new Bike());
                break;
            case Road.SPORTCAR:
                road.addVehicle(new Sportcar());
                break;
            case Road.MOTORCYCLE:
                road.addVehicle(new Motorcycle());
                break;
            default:
                return;
        }
    }

    /**
     * Metodo que remove um veiculo da pista.
     */
    public void removeVehicle() {
        if (road.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nao ha veiculos para remover.");
            return;
        }

        int id = getValidId();
        road.removeVehicle(id);
    }

    /**
     * Metodo que abastece um veiculo.
     */
    public void refuelVehicle() {
        if (road.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nao ha veiculos para remover.");
            return;
        }

        int id;
        Vehicle vehicle;
        float fuel;

        id = getValidId();
        vehicle = road.getById(id);

        if (vehicle == null) {
            return;
        }

        if (vehicle instanceof Bike) {
            JOptionPane.showMessageDialog(null, "Bicicleta nao pode ser abastecida.");
            return;
        }

        fuel = getFuel();
        ((MotorVehicle) vehicle).refuel(fuel);
        JOptionPane.showMessageDialog(null, String.valueOf(fuel) + " litros adicionados ao veiculo " + vehicle);
    }

    /**
     * Metodo que move um veiculo pelo seu ID.
     */
    public void moveById() {
        int id = getValidId();
        Vehicle vehicle = road.getById(id);

        if (vehicle == null) {
            return;
        }

        if (!vehicle.move()) {
            JOptionPane.showMessageDialog(null, vehicle + " nao pode ser movido.");
        }
    }

    /**
     * Metodo que move um veiculo pelo seu tipo.
     */
    public void moveByType() {
        int type = this.getValidType();

        for (Vehicle vehicle : road.getByType(type)) {
            if (!vehicle.move()) {
                JOptionPane.showMessageDialog(null, vehicle + " nao pode ser movido.");
            }
        }
    }

    /**
     * Metodo que move todos os veiculos da pista.
     */
    public void moveAll() {
        for (Vehicle vehicle : road.getVehicles()) {
            if (!vehicle.move()) {
                JOptionPane.showMessageDialog(null, vehicle + " nao pode ser movido.");
            }
        }
    }

    /**
     * Metodo que exibe informacao de todos os veiculos.
     */
    public void printAllIds() {
        if (road.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nao ha veiculos para imprimir.");
            return;
        }

        Vehicle[] vehicles = road.getVehicles();
        String output = "";

        for (int i = 0; i < vehicles.length; i++) {
            output += String.valueOf(i + 1) + " - ";
            output += formatOutput(vehicles[i]);
            output += "\n";
        }

        JOptionPane.showMessageDialog(this, output);
    }

    /**
     * Metodo que exibe informacao de todos os veiculos de um mesmo tipo.
     */
    public void printByType() {
        String output = "";
        int type = this.getValidType();
        Vehicle[] vehicles = road.getByType(type);

        if (vehicles.length == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum veiculo desse tipo.");
            return;
        }

        for (int i = 0; i < vehicles.length; i++) {
            output += String.valueOf(i + 1) + " - ";
            output += formatOutput(vehicles[i]);
            output += "\n";
        }

        JOptionPane.showMessageDialog(null, output);
    }

    /**
     * Metodo que calibra uma unica roda de um veiculo.
     */
    public void calibrateSingle() {
        int tireNumber;
        int id = getValidId();
        Vehicle vehicle = road.getById(id);

        if (vehicle == null) {
            return;
        }

        tireNumber = getValidTire(vehicle.getNumberOfTires());
        vehicle.getTires()[tireNumber].calibrate();
    }

    /**
     * Metodo que calibra todas as rodas de de todos os veiculos do mesmo tipo.
     */
    public void calibrateAllByType() {
        int type = this.getValidType();
        Vehicle[] vehicles = road.getByType(type);

        for (Vehicle vehicle : vehicles) {
            for (Tire tire : vehicle.getTires()) {
                tire.calibrate();
            }
        }
    }

    /**
     * Metodo que esvazia todas as rodas de todos os veiculos do mesmo tipo.
     */
    public void emptyAllByType() {
        int type = this.getValidType();
        Vehicle[] vehicles = road.getByType(type);

        for (Vehicle vehicle : vehicles) {
            for (Tire tire : vehicle.getTires()) {
                tire.empty();
            }
        }
    }

    /**
     * Metodo que salva os dados de todos os veiculos em um arquivo.
     */
    public void saveData() {
        File file = new File("vehicles.dat");
        Vehicle[] vehicles = road.getVehicles();

        if (getConfirmation("Salvar dados")) {
            return;
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(vehicles);

            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados. \n" + e.getMessage());
        }

    }

    /**
     * Metodo que carrega os dados dos veiculos salvos em um arquivo.
     */
    public void loadData() {
        File file = new File("vehicles.dat");
        Vehicle[] vehicles;

        if (getConfirmation("Carregar dados")) {
            return;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            vehicles = (Vehicle[]) objectInputStream.readObject();
            road.setVehicles(vehicles);

            objectInputStream.close();
            fileInputStream.close();
            JOptionPane.showMessageDialog(null, "Dados carregados com sucesso!");
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                JOptionPane.showMessageDialog(null, "Arquivo nao encontrado. \n" + e.getMessage());
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao carregar os dados. \n" + e.getMessage());
            }
        }
    }

    /**
     * Metodo privado que retorna a quantidade de combustivel que o usuario deseja
     * abastecer.
     * 
     * @return float - quantidade de combustivel
     */
    private float getFuel() {
        String input = null;
        float fuel = 0;

        input = JOptionPane.showInputDialog("Insira a quantidade de combustivel.");

        if (input == null) {
            return fuel;
        }

        try {
            fuel = Float.parseFloat(input);
            return fuel;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inserido nao e valido.");

        }

        return getFuel();
    }

    /**
     * Metodo privado que atualiza o estado de todos os botoes.
     */
    private void updateButtons() {
        boolean isEmpty = road.isEmpty();
        boolean isFull = road.isFull();

        for (JButton button : buttons) {
            button.setEnabled(!isEmpty);
        }

        buttons.get(0).setEnabled(!isFull);
        buttons.get(12).setEnabled(!isFull);
    }

    /**
     * Metodo privado que registra os eventos de todos os botoes.
     */
    private void addButtonEvents() {
        buttons.get(0).addActionListener(e -> {
            this.addVehicle();
            this.updateButtons();
            road.repaint();
        });

        buttons.get(1).addActionListener(e -> {
            this.removeVehicle();
            this.updateButtons();
            road.repaint();
        });

        buttons.get(2).addActionListener(e -> {
            this.refuelVehicle();
            road.repaint();
        });

        buttons.get(3).addActionListener(e -> {
            this.moveById();
            road.repaint();
        });

        buttons.get(4).addActionListener(e -> {
            this.moveByType();
            road.repaint();
        });

        buttons.get(5).addActionListener(e -> {
            this.moveAll();
            road.repaint();
        });

        buttons.get(6).addActionListener(e -> {
            this.printAllIds();
        });

        buttons.get(7).addActionListener(e -> {
            this.printByType();
        });

        buttons.get(8).addActionListener(e -> {
            this.calibrateSingle();
        });

        buttons.get(9).addActionListener(e -> {
            this.calibrateAllByType();
        });

        buttons.get(10).addActionListener(e -> {
            this.emptyAllByType();
        });

        buttons.get(11).addActionListener(e -> {
            this.saveData();
        });

        buttons.get(12).addActionListener(e -> {
            this.loadData();
            this.updateButtons();
            road.repaint();
        });
    }

    /**
     * Metodo privado que retorna a confirmacao do usuario.
     * 
     * @param title - titulo da mensagem.
     * @return boolean - confirmacao do usuario.
     */
    private boolean getConfirmation(String title) {
        int answer = JOptionPane.showConfirmDialog(null, "Deseja continuar?", title, JOptionPane.YES_NO_OPTION);
        return (answer != JOptionPane.YES_OPTION);
    }

    /**
     * Metodo que retorna o tipo de veiculo selecionado pelo usuario.
     * 
     * @return int - tipo de veiculo.
     */
    private int getValidType() {
        String input = null;
        int type = -1;

        input = JOptionPane.showInputDialog("Insira o tipo de veiculo desejado.\n" +
                "1 - CARRO\n" +
                "2 - BICICLETA\n" +
                "3 - CARRO ESPORTIVO\n" +
                "4 - MOTOCICLETA");

        if (input == null) {
            return type;
        }

        try {
            type = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tipo inserido nao e valido.");
        }

        switch (type) {
            case Road.CAR:
            case Road.BIKE:
            case Road.SPORTCAR:
            case Road.MOTORCYCLE:
                return type;
            default:
                break;
        }

        return getValidType();
    }

    /**
     * Metodo que retorna um id de veiculo escolhido pelo usuario.
     * 
     * @return int - id de algum veiculo registrado.
     */
    private int getValidId() {
        int[] ids = road.getAllIds();
        String input = null;
        int id = -1;

        input = JOptionPane.showInputDialog("Insira o ID do veiculo desejado.");

        if (input == null) {
            return id;
        }

        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inserido nao e valido.");

        }

        for (int currentId : ids) {
            if (id == currentId) {
                return id;
            }
        }

        JOptionPane.showMessageDialog(null,
                "ID nao encontrado!" +
                        "\nA lista de IDs cadastrados sera exibida em sequencia.");
        printAllIds();
        return getValidId();
    }

    /**
     * Metodo que retorna uma das rodas do veiculo selecionada pelo usuario.
     * 
     * @return int - posicao da roda.
     */
    private int getValidTire(int numberOfTires) {
        String input = null;
        int tireNumber = -1;

        input = JOptionPane.showInputDialog("Insira o numero da roda desejada.");

        if (input == null) {
            return tireNumber;
        }

        try {
            tireNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inserido nao e valido.");
        }

        if (tireNumber >= 1 && tireNumber <= numberOfTires) {
            return tireNumber - 1;
        }

        JOptionPane.showMessageDialog(null, "Valor deve estar entre 1 e " + (numberOfTires) + ".");
        return getValidTire(numberOfTires);
    }

    /**
     * Metodo que retorna uma versao formatada das informacoes de algum veiculo.
     * 
     * @param vehicle - veiculo.
     * @return String - informacao do veiculo.
     */
    private String formatOutput(Vehicle vehicle) {
        Tire[] tires = vehicle.getTires();
        boolean hasPaidIPVA;
        String fuel;

        if (vehicle instanceof Bike) {
            return vehicle + " - " + tires[0] + ", " + tires[1];
        }

        hasPaidIPVA = ((MotorVehicle) vehicle).hasPaidIPVA();
        fuel = String.format("%.2f", ((MotorVehicle) vehicle).getFuel());

        if (vehicle instanceof Motorcycle) {
            return vehicle + " - " + tires[0] + ", " + tires[1] + " | " + fuel
                    + " litros.";
        }

        return vehicle + " - " + tires[0] + ", " + tires[1] + ", " + tires[2] + ", " + tires[3] + " | "
                + fuel + " litros. | " + (hasPaidIPVA ? "IPVA pago." : "Nao pagou IPVA.");
    }
}