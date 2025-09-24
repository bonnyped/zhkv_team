package team.zhkv.simulation;

import java.util.HashSet;
import java.util.Set;

import team.zhkv.actions.Init;
import team.zhkv.actions.InitAllEntities;
import team.zhkv.entities.Creature;
import team.zhkv.render.GameMap;
import team.zhkv.render.Location;

public class Simulation {
    // private List<Action> actions = List.of(new InitAllEntities());
    private GameMap gameMap = new GameMap();
    private int stepsCount = 0;

    public Simulation() {
        Init initAll = new InitAllEntities();
        initAll.init(gameMap);
    }

    private void nextTurn() {
        Set<Location> newCreaturesLocations = new HashSet<>();

        for (Location creature : gameMap.getCreatures()) {
            Creature extarctedCreature = (Creature) gameMap
                    .getLocations()
                    .get(creature);
            extarctedCreature.makeMove(gameMap.getLocations(),
                    creature, newCreaturesLocations);
        }

        gameMap.setCreatures(newCreaturesLocations);
    }

    public void startSimulation() {
        // Scanner scan = new Scanner(System.in);
        // int next = scan.nextInt();
        while (stepsCount != 21) {
            gameMap.render();
            ++stepsCount;
            nextTurn();
            // next = scan.nextInt();
        }
    }

    public void pauseSimulation() {

    }

}
