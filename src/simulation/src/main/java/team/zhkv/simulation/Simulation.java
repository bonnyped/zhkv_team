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
    private List<Action> initActions = List.of(
            new InitAllEntities(),
            new InitGrass(),
            new InitHerbivores());
    private List<Action> turnActions = List.of(
            new TurnRenderer(),
            new TurnMove(),
            new TurnRenderer());
    private int stepsCount = 0;

    private void nextTurn() {
        initActions.stream()
                .map(Init.class::cast)
                .forEach(init -> init.action(gameMap.getEntitiesStorage()));
        turnActions.stream()
                .map(Turn.class::cast)
                .forEach(turn -> turn.action(gameMap.getEntitiesStorage()));
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
