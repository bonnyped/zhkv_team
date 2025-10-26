package team.zhkv.simulation;

/**
 * Factory class for creating Simulation instances.
 *
 * @author bonnyped
 */
public class SimulationFactory {
    /**
     * Creates a new Simulation instance.
     *
     * @return a new Simulation object
     */
    public Simulation createSimulation() {
        return new Simulation();
    }
}
