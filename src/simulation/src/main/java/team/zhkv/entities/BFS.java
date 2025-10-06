package team.zhkv.entities;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import team.zhkv.move.Direction;
import team.zhkv.move.Location;
import team.zhkv.render.GameMap;

public class BFS {
    private GameMap gm;
    private Creature creature;
    private Map<Location, Location> path = new HashMap<>();
    private Deque<Location> forCheck = new ArrayDeque<>();
    private HashSet<Location> checked = new HashSet<>();

    public BFS build(GameMap gm, Location current) {
        this.gm = gm;
        creature = (Creature) gm.getEntity(current);
        checked.add(current);
        forCheck.addAll(addNearestNeighbors(current));
        path.put(null, current);
        buildPath();

        return this;
    }

    public Map<Location, Location> getPath() {
        return path;
    }

    private List<Location> addNearestNeighbors(Location current) {
        List<Location> neighbors = new ArrayList<>();
        for (var direction : Direction.values()) {
            Location neighbor = current.getNeighbor(direction.getDelta());
            if (isNotInChecked(neighbor)
                    && neighbor.isInBounds(GameMap.FIELD_SIZE_MID)
                    && (neighborIsFreeCell(neighbor)
                            || isNeighborEdibleForCreature(current, neighbor))) {
                neighbors.add(neighbor);
                checked.add(neighbor);
            }
        }
        return neighbors;
    }

    private boolean isNotInChecked(Location neighbor) {
        return !checked.contains(neighbor);
    }

    private boolean neighborIsFreeCell(Location neighbor) {
        return gm.getEntity(neighbor) == null;
    }

    private boolean isNeighborEdibleForCreature(Location current,
            Location neighbor) {
        if (gm.getEntity(current) instanceof Eater eater
                && gm.getEntity(neighbor) instanceof Edible edible) {
            // тут обязательно нужно проверить как отрабатывает хербиворе и трава и
            // хербиворе и хищник
            return eater.getFood() == edible.getClass();
        } else {
            return false;
        }
    }

    private void buildPath() {
        Class<? extends Entity> target = creature.getFood();
        while (!forCheck.isEmpty()) {
            Location neighbor = forCheck.pollFirst();
            checked(neighbor);
            // здесь нужно думать о том, как лучше поступить
            if (isNeighborTarget(target, neighbor)) {
                forCheck.clear();
            }
        }
    }

    // public List<Location> getPath() {
    // List<Location> path = new ArrayList<>();
    // while (!forCheck.isEmpty()) {
    // path = forCheck.poll();
    // if (!isTargetReachable(path)) {
    // forCheck.addAll(getPathsWithNeighbors(path));
    // } else {
    // forCheck.clear();
    // }
    // }

    // return path;
    // }

    // private List<Location> getPathsWithNeighbors(List<Location> path) {
    // List<List<Location>> neighbors = new ArrayList<>();
    // List<Location> newPath = new ArrayList<>(path);
    // Location current = path.get(path.size() - 1);
    // Location next = null;

    // for (var neighbor : Direction.values()) {
    // next = current.getNeighbor(neighbor.getDelta());
    // if (isNeighborCorrectLocation(next)) {
    // checked.add(next);
    // newPath.add(next);
    // neighbors.add(newPath);
    // }
    // }
    // return neighbors;
    // }

    // private boolean isNeighborCorrectLocation(Location checking) {
    // return checking.getDx() < GameMap.FIELD_SIZE_MID.getDx()
    // && checking.getDy() < GameMap.FIELD_SIZE_MID.getDy()
    // && !checked.contains(checking)
    // && (isEatableLocation(checking) || isEmptyLocation(checking));
    // }

    // private boolean isEatableLocation(Location checking) {
    // return gm.getEntity(checking) instanceof Edible;
    // // нужна ли проверка хранилища с уже перемещенными сущностями?
    // }

    // private boolean isEmptyLocation(Location checking) {
    // return gm.getEntity(checking) == null;
    // // && checkStorage() проверка на совпадение с
    // // уже обновленными ходями других сущностей
    // }

    // private boolean isTargetReachable(List<Location> path) {
    // Location pathEndLocation = path.get(path.size() - 1);
    // Entity pathTargetEntity = gm.getEntity(pathEndLocation);

    // if (pathTargetEntity instanceof Edible) {
    // return isEdibleTargetForCurrentCreature(pathTargetEntity);
    // } else {
    // return false;
    // }
    // }

    // private boolean isEdibleTargetForCurrentCreature(Entity eatableEntity) {
    // return currentCreature.getFood() == eatableEntity.getClass();
    // }
}
