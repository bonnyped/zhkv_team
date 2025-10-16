package team.zhkv.simulation;

public class App {
    public static void main(String[] args) {
        SimulationFactory factory = new SimulationFactory();
        Simulation simulation = factory.createSimulation();
        Thread simulationThread = new Thread(simulation);

        simulationThread.start();
    }
}
