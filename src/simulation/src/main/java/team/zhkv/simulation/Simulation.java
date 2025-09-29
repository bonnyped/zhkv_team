package team.zhkv.simulation;

import java.util.List;

import team.zhkv.actions.Action;
import team.zhkv.actions.Init;
import team.zhkv.actions.InitAllEntities;
import team.zhkv.actions.TurnGrass;
import team.zhkv.actions.TurnHerbivores;
import team.zhkv.actions.Turn;
import team.zhkv.actions.TurnMove;
import team.zhkv.render.GameMap;
import team.zhkv.render.FullEmojiRenderer;

public class Simulation {
    private GameMap gameMap = new GameMap();
    private FullEmojiRenderer renderer = new FullEmojiRenderer();
    private List<Action> initActions = List.of(
            new InitAllEntities());
    private List<Action> turnActions = List.of(
            new TurnGrass(),
            new TurnHerbivores(),
            new TurnMove()
    // здесь нужно дёргать рендерер и печатать поле
    // вместе с цветом атаки и направлением движения
    // затем нужно будет дернуть удаление старых локаций
    // и внесение новых локаций в общий список
    // new TurnRender()
    );

    private int stepsCount = 0;

    private void nextTurn() {
        turnActions.stream()
                .map(Turn.class::cast)
                .forEach(turn -> turn.action(gameMap));
    }

    public void startSimulation() {
        initActions.stream()
                .map(Init.class::cast)
                .forEach(init -> init.action(gameMap));
        while (stepsCount != 21) {
            renderer.render(gameMap.getWholeMapEntities());
            ++stepsCount;
            nextTurn();
            renderer.render(gameMap.getWholeMapEntities(), gm.getAllStepChanges());
        }
    }

    public void pauseSimulation() {

    }

}
