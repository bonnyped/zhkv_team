package team.zhkv;

import team.zhkv.simulation.Simulation;

public class App {
    public static void main(String[] args) {
        SimulationFactory factory = new SimulationFactory();
        Simulation simulation = factory.createSimulation();
        simulation.startSimulation();

    }
}
