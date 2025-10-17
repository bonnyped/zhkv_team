package team.zhkv.simulation;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulationRunner implements Runnable {
    private static final long TURN_DELAY_MS = 300;
    private static final Logger logger = LoggerFactory.getLogger(
            SimulationRunner.class);

    private volatile boolean running;
    private volatile boolean paused;

    private final Simulation simulation;
    private final CountDownLatch shutDownLatch;

    private ScheduledExecutorService simulationExecutor;

    public SimulationRunner(Simulation simulation) {
        this.simulation = simulation;
        shutDownLatch = new CountDownLatch(1);

    }

    @Override
    public void run() {
        running = true;
        paused = false;
        try {
            simulation.executeInit();
            startSimulation();
            while (running) {
                shutDownLatch.await();
            }
        } catch (Exception e) {
            logger.warn("Simulation error");
        } finally {
            shutdown();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void countDownShutDownLatch() {
        shutDownLatch.countDown();
    }

    private void startSimulation() {
        simulationExecutor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r, "Simulation-Thread");
            thread.setDaemon(false);
            return thread;
        });

        simulationExecutor.scheduleWithFixedDelay(
                () -> {
                    if (!paused && running) {
                        simulation.executeTurn();
                    }
                },
                TURN_DELAY_MS,
                TURN_DELAY_MS,
                TimeUnit.MILLISECONDS);
    }

    private void shutdown() {
        running = false;

        if (simulationExecutor == null
                || simulationExecutor.isShutdown()) {
            return;
        }

        simulationExecutor.shutdown();

        try {
            checkTerminate(true);
            checkTerminate(false);
        } catch (InterruptedException e) {
            simulationExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        logger.info("Shutdown simulation comlete!");
    }

    private void checkTerminate(boolean isFirstCheck)
            throws InterruptedException {
        if (!simulationExecutor.isTerminated()
                && !simulationExecutor.awaitTermination(
                        TURN_DELAY_MS,
                        TimeUnit.MILLISECONDS)) {
            if (isFirstCheck) {
                simulationExecutor.shutdownNow();
            } else {
                logger.error("Executor did not terminate");
            }
        }
    }
}
