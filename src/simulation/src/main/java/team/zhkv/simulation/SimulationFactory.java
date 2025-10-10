package team.zhkv.simulation;

public class SimulationFactory {
    public Simulation createSimulation() {
        return new Simulation();
    }

    public Simulation createSimulation(int x, int y) {
        return new Simulation(x, y);
    }
}
