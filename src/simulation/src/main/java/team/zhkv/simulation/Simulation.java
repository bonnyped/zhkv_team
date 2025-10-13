package team.zhkv.simulation;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.map.GameState;
import team.zhkv.core.interfaces.*;
import team.zhkv.actions.*;
import team.zhkv.rendering.*;

public class Simulation implements Runnable {
        private static final Logger logger = LoggerFactory.getLogger(
                        Simulation.class);
        protected volatile boolean running;
        protected volatile boolean paused;
        private GameState gs;
        private List<IAction> initActions = List.of(
                        new InitAllEntities());
        private List<IAction> turnActions = List.of(
                        new TurnMove(),
                        new TurnGrass(),
                        new TurnHerbivores());
        private IRenderable renderer = new Renderer();

        private void executeNextTurn() {
                turnActions.stream()
                                .map(Turn.class::cast)
                                .forEach(turn -> turn.action(gs));
        }

        @Override
        public void run() {
                running = true;
                pauseSimulation();
                startSimulation();
        }

        private void startSimulation() {
                initActions.stream()
                                .map(Init.class::cast)
                                .forEach(init -> init.action(gs.get));
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
                                executeNextTurn();
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
