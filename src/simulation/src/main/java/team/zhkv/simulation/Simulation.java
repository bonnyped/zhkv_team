package team.zhkv.simulation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

import team.zhkv.actions.Init;
import team.zhkv.actions.InitAllEntities;
import team.zhkv.entities.Creature;
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
        ArrayList<Location> creatures = new ArrayList<>(gameMap.getCreatures());
        HashSet<Location> newCreaturesLocations = new HashSet<>();
        for (int i = 0; i < creatures.size(); i++) {
            Location step = bfs.buildPath(
                    creatures.get(i), storage.getFieldSize());
            newCreaturesLocations.add(step);
        }
        gameMap.setCreatures(newCreaturesLocations);
    }

    public void startSimulation() {
        while (stepsCount == 0) {
            gameMap.render();
            ++stepsCount;
            nextTurn();
        }
    }

    public void pauseSimulation() {

    }

}
