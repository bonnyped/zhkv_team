package team.zhkv.entities;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

import team.zhkv.move.Direction;
import team.zhkv.move.Location;
import team.zhkv.render.GameMap;

public class BFS {
    private GameMap gm;
    private Creature currentCreature;
    private Deque<List<Location>> forCheck = new ArrayDeque<>();
    private HashSet<Location> checked = new HashSet<>();

    public BFS build(GameMap gm, Location current) {
        this.gm = gm;
        currentCreature = (Creature) gm.getEntity(current);
        forCheck.addAll(getPathsWithNeighbors(List.of(current)));

        return this;
    }

    public List<Location> getNextStep() {
        List<Location> path = new ArrayList<>();
        while (!forCheck.isEmpty()) {
            path = forCheck.poll();
            if (!isTargetReachable(path)) {
                forCheck.addAll(getPathsWithNeighbors(path));
            } else {
                forCheck.clear();
            }
        }

        return path;
    }

    private List<List<Location>> getPathsWithNeighbors(List<Location> path) {
        List<List<Location>> neighbors = new ArrayList<>();
        Location current = path.get(path.size() - 1);
        Location next = null;

        for (var neighbor : Direction.values()) {
            next = current.getNeighbor(neighbor.getDelta());
            if (isNeighborCorrectLocation(next)) {
                List<Location> newPath = new ArrayList<>(path);
                newPath.add(next);
                neighbors.add(newPath);
            }
        }
        return neighbors;
    }

    private boolean isNeighborCorrectLocation(Location checking) {
        return checking.getDx() < GameMap.FIELD_SIZE_MID.getDx()
                && checking.getDy() < GameMap.FIELD_SIZE_MID.getDy()
                && !checked.contains(checking)
                && (isEatableLocation(checking) || isEmptyLocation(checking));
    }

    private boolean isEatableLocation(Location checking) {
        return gm.getEntity(checking) instanceof Eatable;
        // нужна ли проверка хранилища с уже перемещенными сущностями?
    }

    private boolean isEmptyLocation(Location checking) {
        return gm.getEntity(checking) == null;
        // && checkStorage() проверка на совпадение с
        // уже обновленными ходями других сущностей
    }

    private boolean isTargetReachable(List<Location> path) {
        Location pathEndLocation = path.get(path.size() - 1);
        Entity pathTargetEntity = gm.getEntity(pathEndLocation);

        if (pathTargetEntity instanceof Eatable) {
            return isEdibleTargetForCurrentCreature(pathTargetEntity);
        } else {
            return false;
        }
    }

    private boolean isEdibleTargetForCurrentCreature(Entity eatableEntity) {
        return currentCreature.getFood() == eatableEntity.getClass();
    }
}
