package team.zhkv.simulation;

/**
 * Entry point for the simulation application.
 * Initializes and starts the simulation and input listener threads.
 *
 * @author bonnyped
 */
public class App {
        private static final int DELAY_MS = 1000;

        /**
         * Main method to launch the simulation.
         *
         * @param args command-line arguments (not used)
         */
        public static void main(String[] args) {
                SimulationFactory factory = new SimulationFactory();

                SimulationRunner simulationRunner = new SimulationRunner(
                                DELAY_MS, factory.createSimulation());

                Thread simulationThread = new Thread(simulationRunner,
                                "Simulation-Tread");

                InputListner inputListner = new InputListner(simulationRunner,
                                simulationRunner.getSimulationState());

                Thread inputListnerThread = new Thread(inputListner,
                                "Input-Listner-Thread");

                inputListnerThread.setDaemon(true);
                inputListnerThread.start();
                simulationThread.start();
        }
}
