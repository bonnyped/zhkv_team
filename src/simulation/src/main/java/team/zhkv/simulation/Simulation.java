package team.zhkv.simulation;

import java.util.List;

import team.zhkv.actions.Action;
import team.zhkv.actions.Init;
import team.zhkv.actions.InitAllEntities;
import team.zhkv.actions.TurnGrass;
import team.zhkv.actions.TurnHerbivores;
import team.zhkv.actions.Turn;
import team.zhkv.actions.TurnMove;
import team.zhkv.render.FinalRenderer;
import team.zhkv.render.GameMap;
import team.zhkv.render.Renderable;

public class Simulation {
        private GameMap gameMap = new GameMap();
        private List<Action> initActions = List.of(
                        new InitAllEntities());
        private List<Action> turnActions = List.of(
                        new TurnGrass(),
                        new TurnHerbivores(),
                        new TurnMove());
        private Renderable renderer;

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
                        new FinalRenderer().render(gameMap);
                        ++stepsCount;
                        nextTurn();
                        gameMap.applyChanges();
                }
        }

        public void pauseSimulation() {

        }

}
