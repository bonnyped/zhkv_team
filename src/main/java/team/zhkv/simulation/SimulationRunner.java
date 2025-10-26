package team.zhkv.simulation;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.core.interfaces.ISimulation;

/**
 * Runs the simulation in a separate thread and manages its lifecycle.
 *
 * @author bonnyped
 */
public class SimulationRunner implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(
            SimulationRunner.class);

    private final SimulationScheduler scheduler;
    private final SimulationState simulationState;

    private final ISimulation simulation;
    private final CountDownLatch shutDownLatch = new CountDownLatch(1);

    /**
     * Constructs a SimulationRunner.
     *
     * @param delayMs    the delay between simulation turns in milliseconds
     * @param simulation the simulation instance to run
     */
    public SimulationRunner(int delayMs, ISimulation simulation) {
        simulationState = new SimulationState();
        this.simulation = simulation;
        scheduler = new SimulationScheduler(() -> {
            if (!simulationState.isPaused()) {
                simulation.executeTurn();
            }
        }, delayMs);
    }

    /**
     * Starts the simulation and manages its execution loop.
     */
    @Override
    public void run() {
        simulationState.setRunning(true);
        simulationState.setPaused(false);
        try {
            simulation.executeInit();
            scheduler.start();
            shutDownLatch.await();
        } catch (Exception e) {
            logger.warn("Simulation error");
        } finally {
            scheduler.shutdown();
        }
    }

    /**
     * Returns the current simulation state.
     *
     * @return the SimulationState object
     */
    public SimulationState getSimulationState() {
        return simulationState;
    }

    /**
     * Signals the simulation to shut down.
     */
    public void countDownShutDownLatch() {
        shutDownLatch.countDown();
    }
}
