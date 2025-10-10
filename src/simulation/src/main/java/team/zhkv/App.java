package team.zhkv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.simulation.Simulation;
import team.zhkv.simulation.SimulationFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SimulationFactory factory = new SimulationFactory();
        Simulation simulation = factory.createSimulation();
        Thread simulationThread = new Thread(simulation);

        simulationThread.start();

        try {
            simulationThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Main Thread was interrupted!");
        }
    }
}
