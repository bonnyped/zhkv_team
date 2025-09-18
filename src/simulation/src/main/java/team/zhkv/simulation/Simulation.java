package team.zhkv.simulation;

import team.zhkv.actions.Action;
import team.zhkv.actions.InitAllEntities;
import team.zhkv.actions.Turn;
import team.zhkv.render.GameMap;
import team.zhkv.render.MapRenderer;

public class Simulation {
    private GameMap gm = new GameMap();
    private MapRenderer renderer = new MapRenderer(gm.getEntities());
    private int stepsCount;
    private Action initAll = new InitAllEntities();
    private Action[] turnActions = {};

    private void nextTurn() {
        initAll.init(gm.getStorage());
        gm.setEntities(initAll.getLocations());
        renderer.render();
    }

    public void startSimulation() {
        while (true) {
            nextTurn();
        }
    }

    public void pauseSimulation() {

    }

}
