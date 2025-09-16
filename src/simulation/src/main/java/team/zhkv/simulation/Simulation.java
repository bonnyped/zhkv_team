package team.zhkv.simulation;

import team.zhkv.Init;
import team.zhkv.actions.Action;
import team.zhkv.actions.InitAllEntities;
import team.zhkv.actions.Turn;
import team.zhkv.render.GameMap;

public class Simulation {
    private GameMap gm = new GameMap();
    private int stepsCount;
    private Action initActions = new InitAllEntities(gm.getEntities(),
            gm.getStorage(),
            gm.getCreatures());
    private Action[] turnActions = {};

    private Action[] gettIniActions() {
        return {,
                                };
    }

    private void nextTurn() {
        if (gm.getEntities() == null) {
            initActions[0].execute();
        }
    }

    public void startSimulation() {
        while (true) {
            nextTurn();
        }
    }

    public void pauseSimulation() {

    }

}
