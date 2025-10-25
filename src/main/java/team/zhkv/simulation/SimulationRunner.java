package team.zhkv.simulation;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.core.interfaces.ISimulation;

public class SimulationRunner implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(
            SimulationRunner.class);

    private final SimulationScheduler scheduler;
    private final SimulationState simulationState;

    private final ISimulation simulation;
    private final CountDownLatch shutDownLatch = new CountDownLatch(1);

    public SimulationRunner(int delayMs, ISimulation simulation) {
        simulationState = new SimulationState();
        this.simulation = simulation;
        scheduler = new SimulationScheduler(() -> {
            if (!simulationState.isPaused()) {
                simulation.executeTurn();
            }
        }, delayMs);
    }

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

    public SimulationState getSimulationState() {
        return simulationState;
    }

    public void countDownShutDownLatch() {
        shutDownLatch.countDown();
    }

}
