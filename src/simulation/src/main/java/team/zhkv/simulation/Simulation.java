package team.zhkv.simulation;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.GameMap;
import team.zhkv.actions.*;
import team.zhkv.service.Renderer;
import team.zhkv.service.impl.Renderable;
import team.zhkv.service.impl.IAction;

public class Simulation implements Runnable {
        private static final Logger logger = LoggerFactory.getLogger(
                        Simulation.class);
        protected volatile boolean running;
        protected volatile boolean paused;
        private GameMap gameMap;
        private List<IAction> initActions = List.of(
                        new InitAllEntities());
        private List<IAction> turnActions = List.of(
                        new TurnMove(),
                        new TurnGrass(),
                        new TurnHerbivores());
        private Renderable renderer = new Renderer();

        private int stepsCount = 0;

        private void nextTurn() {
                turnActions.stream()
                                .map(Turn.class::cast)
                                .forEach(turn -> turn.action(gameMap));
        }

        @Override
        public void run() {
                running = true;
                pauseSimulation();
                startSimulation();
        }

        public Simulation() {
                gameMap = new GameMap();
        }

        public Simulation(int x, int y) {
                gameMap = new GameMap(x, y);
        }

        private void startSimulation() {
                initActions.stream()
                                .map(Init.class::cast)
                                .forEach(init -> init.action(gameMap));
                while (true) {
                        if (!paused) {
                                try {
                                        Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                        logger.warn("Thread: {} was interrupted",
                                                        Thread.currentThread().getName());
                                }
                                ++stepsCount;
                                renderer.render(gameMap, stepsCount);
                                nextTurn();
                        }
                }
        }

        public void pauseSimulation() {
                Thread thread = new Thread(() -> {
                        Scanner scanner = new Scanner(System.in);

                        while (true) {
                                if (scanner.hasNext("p")) {
                                        scanner.nextLine();
                                        paused = !paused;
                                }
                        }
                });

                thread.setDaemon(true);
                thread.start();
        }

}
