package team.zhkv.simulation;

import java.util.List;

import team.zhkv.actions.Action;
import team.zhkv.actions.Init;
import team.zhkv.actions.InitAllEntities;
import team.zhkv.actions.InitGrass;
import team.zhkv.actions.InitHerbivores;
import team.zhkv.actions.Turn;
import team.zhkv.actions.TurnMove;
import team.zhkv.actions.TurnRender;
import team.zhkv.render.GameMap;
import team.zhkv.render.MapRenderer;

public class Simulation {
    private GameMap gameMap = new GameMap();
    private List<Action> initActions = List.of(
            new InitAllEntities(),
            new InitGrass(),
            new InitHerbivores());
    private List<Action> turnActions = List.of(
            new TurnRender(),
            new TurnMove()
    // здесь нужно дёргать рендерер и печатать поле
    // вместе с цветом атаки и направлением движения
    // затем нужно будет дернуть удаление старых локаций
    // и внесение новых локаций в общий список
    // new TurnRender()
    );
    private int stepsCount = 0;

    private MapRenderer renderer = new MapRenderer(gameMap);

    private void nextTurn() {
        initActions.stream()
                .map(Init.class::cast)
                .forEach(init -> init.action(gameMap));
        turnActions.stream()
                .map(Turn.class::cast)
                .forEach(turn -> {
                    if (turn.getClass() == TurnRender.class) {
                        turn.action(renderer);
                    } else {
                        turn.action(gameMap);
                    }
                });
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
