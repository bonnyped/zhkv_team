package team.zhkv;

import team.zhkv.render.Location;
import team.zhkv.simulation.Simulation;

public class App {
    public static final Location FIELD_SIZE = new Location(5, 5);

    public static void main(String[] args) {
        SimulationFactory factory = new SimulationFactory();
        Simulation simulation = factory.createSimulation();
        simulation.startSimulation();
    }
}
