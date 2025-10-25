package team.zhkv.simulation;

import java.util.List;

import team.zhkv.actions.*;
import team.zhkv.core.interfaces.IAction;
import team.zhkv.core.interfaces.ISimulation;
import team.zhkv.map.GameManager;
import team.zhkv.rendering.IRenderable;
import team.zhkv.rendering.Renderer;

public class Simulation implements ISimulation {
        private final GameManager gm;
        private final List<IAction> initActions;
        private final List<IAction> turnActions;
        private final IRenderable renderer;

        public Simulation() {
                gm = new GameManager();
                initActions = List.of(
                                new InitAllEntities());
                turnActions = List.of(
                                new TurnPathfinder(),
                                new TurnMove(),
                                new TurnDamage(),
                                new TurnEat(),
                                new TurnRespawn(),
                                new TurnRemove());
                renderer = new Renderer();
        }

        @Override
        public void executeInit() {
                initActions.stream()
                                .map(Init.class::cast)
                                .forEach(init -> init.action(gm));
        }

        @Override
        public void executeTurn() {
                gm.incrementTurn();
                renderer.render(gm);
                turnActions.stream()
                                .map(Turn.class::cast)
                                .forEach(turn -> turn.action(gm));

        }

}
