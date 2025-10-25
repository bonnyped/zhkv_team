package team.zhkv.simulation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulationScheduler {
    private static final Logger logger = LoggerFactory.getLogger(
            SimulationScheduler.class);
    private final ScheduledExecutorService executor;
    private final Runnable turnTask;
    private final long delayMs;

    public SimulationScheduler(Runnable turnTask, long delayMs) {
        this.turnTask = turnTask;
        this.delayMs = delayMs;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        executor.scheduleWithFixedDelay(turnTask,
                delayMs,
                delayMs,
                TimeUnit.MILLISECONDS);
    }

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
