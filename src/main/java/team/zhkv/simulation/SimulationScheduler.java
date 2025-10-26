package team.zhkv.simulation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Schedules and manages the execution of simulation turns at fixed intervals.
 *
 * @author bonnyped
 */
public class SimulationScheduler {
    private static final Logger logger = LoggerFactory.getLogger(
            SimulationScheduler.class);
    private final ScheduledExecutorService executor;
    private final Runnable turnTask;
    private final long delayMs;

    /**
     * Constructs a SimulationScheduler.
     *
     * @param turnTask the task to execute each turn
     * @param delayMs  the delay between turns in milliseconds
     */
    public SimulationScheduler(Runnable turnTask, long delayMs) {
        this.turnTask = turnTask;
        this.delayMs = delayMs;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * Starts scheduling the turn task at fixed intervals.
     */
    public void start() {
        executor.scheduleWithFixedDelay(turnTask,
                delayMs,
                delayMs,
                TimeUnit.MILLISECONDS);
    }

    /**
     * Shuts down the scheduler and waits for termination.
     */
    public void shutdown() {
        if (executor.isShutdown()) {
            return;
        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(delayMs, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(delayMs, TimeUnit.MILLISECONDS)) {
                    logger.error("Executor did not terminate");
                }
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        logger.info("Shutdown simulation complete!");
    }
}
