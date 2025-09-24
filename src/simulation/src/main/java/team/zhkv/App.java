package team.zhkv;

import team.zhkv.render.Location;
import team.zhkv.simulation.Simulation;

public class App {
    public static final Location FIELD_SIZE_MIN = new Location(11, 11);
    public static final Location FIELD_SIZE_MID = new Location(33, 33);
    public static final Location FIELD_SIZE_MAX = new Location(77, 77);

    public static void main(String[] args) {
        SimulationFactory factory = new SimulationFactory();
        Simulation simulation = factory.createSimulation();
        simulation.startSimulation();
    }
}
