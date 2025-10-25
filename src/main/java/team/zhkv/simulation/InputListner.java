package team.zhkv.simulation;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputListner implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(
            InputListner.class);
    private final SimulationRunner simulationRunner;
    private final SimulationState simulationState;

    public InputListner(SimulationRunner simulationRunner, SimulationState simulationState) {
        this.simulationState = simulationState;
        this.simulationRunner = simulationRunner;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (simulationState.isRunning()) {
            if (scanner.hasNext()) {
                String input = scanner.nextLine()
                        .trim()
                        .toLowerCase();
                handleInput(input);
            }
        }
    }

    @SuppressWarnings("java:S3078")
    private void handleInput(String input) {
        switch (input) {
            case "p":
                simulationState.setPaused(!simulationState.isPaused());
                logger.info("Program {}", simulationState.isPaused()
                        ? "paused"
                        : "resumed");
                break;
            case "exit", "q":
                simulationState.setRunning(false);
                simulationRunner.countDownShutDownLatch();
                logger.info("Shutting down simulation...");
                break;

            default:
                logger.debug("Unknown command {}", input);
        }
    }
}
