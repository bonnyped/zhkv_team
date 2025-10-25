package team.zhkv.simulation;

public class App {
        public static void main(String[] args) {
                SimulationFactory factory = new SimulationFactory();
                SimulationRunner simulationRunner = new SimulationRunner(
                                factory.createSimulation());
                Thread simulationThread = new Thread(simulationRunner,
                                "Simulation-Tread");
                InputListner inputListner = new InputListner(simulationRunner);
                Thread inputListnerThread = new Thread(inputListner,
                                "Input-Listner-Thread");

                inputListnerThread.setDaemon(true);
                inputListnerThread.start();
                simulationThread.start();
        }
}
