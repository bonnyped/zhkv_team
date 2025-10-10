package team.zhkv.simulation;

import java.util.Scanner;

public class SimulationAI {
    private volatile boolean running = false;
    private volatile boolean paused = false;
    private Thread simulationThread;
    private Thread inputThread;
    private final Object pauseLock = new Object();

    public void start() {
        // Запуск потока симуляции
        startSimulation();

        // Запуск потока для обработки команд
        startInputListener();
    }

    private void startSimulation() {
        if (running) {
            return;
        }

        running = true;
        paused = false;

        simulationThread = new Thread(() -> {
            while (running) {
                synchronized (pauseLock) {
                    while (paused) {
                        try {
                            pauseLock.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }

                // Обновление игры
                updateGameState();
                render();

                try {
                    Thread.sleep(16); // ~60 FPS
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        simulationThread.start();
    }

    private void startInputListener() {
        inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Управление:");
            System.out.println("  p - пауза");
            System.out.println("  r - продолжить");
            System.out.println("  s - остановить");
            System.out.println("  q - выход");

            while (running) {
                if (scanner.hasNextLine()) {
                    String command = scanner.nextLine().trim().toLowerCase();

                    switch (command) {
                        case "p":
                        case "pause":
                            pauseSimulation();
                            System.out.println("Симуляция приостановлена");
                            break;

                        case "r":
                        case "resume":
                            resumeSimulation();
                            System.out.println("Симуляция возобновлена");
                            break;

                        case "s":
                        case "stop":
                            stopSimulation();
                            System.out.println("Симуляция остановлена");
                            break;

                        case "q":
                        case "quit":
                            stopSimulation();
                            System.out.println("Выход из программы");
                            System.exit(0);
                            break;

                        default:
                            System.out.println("Неизвестная команда: " + command);
                    }
                }
            }

            scanner.close();
        });

        inputThread.setDaemon(true); // поток завершится с основной программой
        inputThread.start();
    }

    private void pauseSimulation() {
        synchronized (pauseLock) {
            paused = true;
        }
    }

    private void resumeSimulation() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    private void stopSimulation() {
        running = false;
        resumeSimulation();
        if (simulationThread != null) {
            simulationThread.interrupt();
        }
    }
}
