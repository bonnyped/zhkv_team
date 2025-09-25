package team.zhkv.simulation;

import java.util.List;

import team.zhkv.actions.Action;
import team.zhkv.actions.Init;
import team.zhkv.actions.InitAllEntities;
import team.zhkv.actions.InitGrass;
import team.zhkv.actions.InitHerbivores;
import team.zhkv.actions.Turn;
import team.zhkv.actions.TurnMove;
import team.zhkv.actions.TurnRenderer;
import team.zhkv.render.GameMap;

public class Simulation {
    private GameMap gameMap = new GameMap();
    private List<Action> initActions = List.of(new InitAllEntities(gameMap),
            new InitGrass(gameMap),
            new InitHerbivores(gameMap));
    private List<Action> turnActions = List.of(new TurnRenderer(gameMap),
            new TurnMove(gameMap), new TurnRenderer(gameMap));
    private int stepsCount = 0;

    private void nextTurn() {
        initActions.stream().map(Init.class::cast).forEach(Init::init);
        turnActions.stream().map(Turn.class::cast).forEach(Turn::turn);
    }

    public void startSimulation() {
        while (stepsCount != 21) {
            ++stepsCount;
            nextTurn();
        }
    }

    public void pauseSimulation() {

    }

}
