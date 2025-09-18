package team.zhkv.simulation;

import team.zhkv.actions.Action;
import team.zhkv.actions.InitAllEntities;
import team.zhkv.render.GameMap;
import team.zhkv.render.MapRenderer;
import team.zhkv.utils.PropertiesStorage;

public class Simulation {
    private PropertiesStorage storage = new PropertiesStorage();
    private GameMap gm;
    private int stepsCount = 0;

    public Simulation() {
        Action initAll = new InitAllEntities();
        initAll.init(storage);
        gm = new GameMap(storage.getLocations(), storage.getCreatures(),
                storage.getFieldSize());

    }

    private void nextTurn() {
        MapRenderer renderer = gm.getRenderer();
        renderer.render(gm.getLocations());
    }

    public void startSimulation() {
        while (stepsCount == 0) {
            ++stepsCount;
            nextTurn();
        }
    }

    public void pauseSimulation() {

    }

}
