package team.zhkv.core.interfaces;

/**
 * Interface for simulation logic, including initialization and turn execution.
 *
 * @author bonnyped
 */
public interface ISimulation {
    /**
     * Executes all initialization actions for the simulation.
     */
    void executeInit();

    /**
     * Executes a single simulation turn.
     */
    void executeTurn();
}
