package team.zhkv.simulation;

import team.zhkv.actions.Init;
import team.zhkv.actions.InitAllEntities;
import team.zhkv.entities.Location;
import team.zhkv.render.GameMap;
import team.zhkv.utils.BFS;
import team.zhkv.utils.PropertiesStorage;

public class Simulation {
    private PropertiesStorage storage = new PropertiesStorage();
    private GameMap gameMap;
    private int stepsCount = 0;

    public Simulation() {
        Init initAll = new InitAllEntities();
        initAll.init(storage);
        gameMap = new GameMap(storage.getLocations(), storage.getCreatures(),
                storage.getFieldSize());
    }

    private void nextTurn() {
        BFS bfs = new BFS(gameMap.getLocations());
        for (Location creature : gameMap.getCreatures()) {
            bfs.build(creature, storage.getFieldSize());

        }

    }

    public void startSimulation() {
        gameMap.render();
        while (stepsCount == 0) {
            ++stepsCount;
            nextTurn();
        }
    }

    public void pauseSimulation() {

    }

}
