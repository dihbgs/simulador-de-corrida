import javax.swing.*;
import java.awt.*;

public class SimulatorGUI extends JFrame {
    private Road road;

    public SimulatorGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Simulator 2.0");
        setSize(1024, 640);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        Road canvasPanel = new Road();
        canvasPanel.setPreferredSize(new Dimension(1024, 1280));

        JScrollPane scrollPane = new JScrollPane(canvasPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel leftComponent = new JPanel();
        leftComponent.setLayout(new BorderLayout());
        leftComponent.add(scrollPane, BorderLayout.CENTER);

        JPanel rightComponent = new JPanel();
        rightComponent.setLayout(new GridLayout(0, 1));

        JButton addVehicle = new JButton("Adicionar veiculo");
        JButton removeVehicle = new JButton("Remover veiculo");
        JButton refuelVehicle = new JButton("Abastecer veiculo");
        JButton moveById = new JButton("Mover por ID");
        JButton moveByType = new JButton("Mover por tipo");
        JButton moveAll = new JButton("Mover todos");
        JButton printAll = new JButton("Imprimir todos");
        JButton printByType = new JButton("Imprimir por tipo");
        JButton calibrateSingle = new JButton("Esvaziar/Calibrar roda");
        JButton calibrateAllByType = new JButton("Calibrar rodas por tipo");
        JButton emptyAllByType = new JButton("Esvaziar rodas por tipo");
        JButton saveData = new JButton("Salvar em arquivo");
        JButton loadData = new JButton("Carregar de arquivo");

        addVehicle.addActionListener(e -> {

        });

        rightComponent.add(addVehicle);
        rightComponent.add(removeVehicle);
        rightComponent.add(refuelVehicle);
        rightComponent.add(moveById);
        rightComponent.add(moveByType);
        rightComponent.add(moveAll);
        rightComponent.add(printAll);
        rightComponent.add(printByType);
        rightComponent.add(calibrateSingle);
        rightComponent.add(calibrateAllByType);
        rightComponent.add(emptyAllByType);
        rightComponent.add(saveData);
        rightComponent.add(loadData);

        mainPanel.add(leftComponent, BorderLayout.WEST);
        mainPanel.add(rightComponent, BorderLayout.EAST);

        add(mainPanel);

        this.road = canvasPanel;

        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(this.getWidth(), 640);
    }

    private int getValidId() {
        int[] ids = road.getAllIds();

        // to do

        return ids[0];
    }

    public Road getRoad() {
        return this.road;
    }

    public static void main(String[] args) {
        SimulatorGUI simulator = new SimulatorGUI();

        for(int i=0; i<20; i++) {
            simulator.getRoad().addVehicle(new Bicycle().setPosition(0, i));
        }

        simulator.setVisible(true);
    }
}