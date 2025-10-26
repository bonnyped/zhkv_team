package team.zhkv.simulation;

/**
 * Represents the state of the simulation, including whether it is running or
 * paused.
 * Provides thread-safe access to state flags.
 *
 * @author bonnyped
 */
public class SimulationState {
    /** Indicates if the simulation is currently running. */
    private volatile boolean running;
    /** Indicates if the simulation is currently paused. */
    private volatile boolean paused;

    /**
     * Returns whether the simulation is paused.
     *
     * @return true if the simulation is paused, false otherwise
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Sets the paused state of the simulation.
     *
     * @param paused true to pause the simulation, false to resume
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * Returns whether the simulation is running.
     *
     * @return true if the simulation is running, false otherwise
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the running state of the simulation.
     *
     * @param running true to mark the simulation as running, false to stop
     */
    public void setRunning(boolean running) {
        this.running = running;
    }
}
