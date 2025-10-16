package team.zhkv.simulation;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.map.GameMap;
import team.zhkv.core.interfaces.*;
import team.zhkv.actions.*;
import team.zhkv.rendering.*;

public class Simulation implements Runnable {
        private static final long TURN_DELAY_MS = 1500;
        private static final Logger logger = LoggerFactory.getLogger(
                        Simulation.class);

        protected volatile boolean running;
        protected volatile boolean paused;

        private final GameMap gm;
        private final List<IAction> initActions;
        private final List<IAction> turnActions;
        private final IRenderable renderer;

        private ScheduledExecutorService simulationExecutor;
        private Thread inputThread;

        public Simulation() {
                gm = new GameMap();
                initActions = List.of(
                                new InitAllEntities());
                turnActions = List.of(
                                new TurnPathfinder(),
                                new TurnMove(),
                                new TurnDamage(),
                                new TurnEat(),
                                new TurnRespawn(),
                                new TurnRemove());
                renderer = new Renderer();
        }

        @Override
        public void run() {
                try {
                        initialize();
                        startSimulation();
                        while (running) {
                                Thread.sleep(200); // или более "тонкое" ожидание через wait()/notify()
                        }
                } catch (Exception e) {
                        logger.warn("Simulation error");
                } finally {
                        shutdown();
                }
        }

        private void initialize() {
                running = true;
                paused = false;

                initActions.stream()
                                .map(Init.class::cast)
                                .forEach(init -> init.action(gm));
                startInputListner();
        }

        private void startInputListner() {
                inputThread = new Thread(() -> {
                        Scanner scanner = new Scanner(System.in);

                        while (running) {
                                if (scanner.hasNext()) {
                                        String input = scanner.nextLine()
                                                        .trim()
                                                        .toLowerCase();
                                        handleInput(input);
                                }
                        }

                }, "Input-Listner-Thread");

                inputThread.setDaemon(true);
                inputThread.start();

        }

        @SuppressWarnings("java:S3078")
        private void handleInput(String input) {
                switch (input) {
                        case "p":
                                paused = !paused;
                                logger.info("Program {}", paused
                                                ? "paused"
                                                : "resumed");
                                break;
                        case "exit", "q":
                                running = false;
                                logger.info("Shutting down simulation...");
                                break;

                        default:
                                logger.debug("Unknown command {}", input);
                }
        }

        private void startSimulation() {
                simulationExecutor = Executors.newSingleThreadScheduledExecutor(r -> {
                        Thread thread = new Thread(r, "Simulation-Thread");
                        thread.setDaemon(false);
                        return thread;
                });

                simulationExecutor.scheduleWithFixedDelay(
                                this::executeTurn,
                                TURN_DELAY_MS,
                                TURN_DELAY_MS,
                                TimeUnit.MILLISECONDS);

                System.out.println("BUY!!");
        }

        private void executeTurn() {
                if (!paused && running) {
                        gm.incrementTurn();
                        renderer.render(gm);
                        turnActions.stream()
                                        .map(Turn.class::cast)
                                        .forEach(turn -> turn.action(gm));
                }
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
                                                TURN_DELAY_MS, TimeUnit.MILLISECONDS)) {
                        if (isFirstCheck) {
                                simulationExecutor.shutdownNow();
                        } else {
                                logger.error("Executor did not terminate");
                        }
                }
        }

}
