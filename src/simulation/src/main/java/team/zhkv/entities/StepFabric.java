package team.zhkv.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import team.zhkv.move.Direction;
import team.zhkv.move.Location;
import team.zhkv.render.GameMap;
import team.zhkv.utils.Neighbor;
import team.zhkv.utils.NeighborsDeque;

public class StepFabric {
    // к удалению
    private GameMap gm;
    // к удалению
    private Map<Location, Entity> locations;
    private Map<Location, Location> toMove;
    private Set<Location> toRemove;
    private Creature extractedCreature;
    private Location extractedCreatureLocation;
    private NeighborsDeque forCheck = new NeighborsDeque();
    private HashSet<Location> checked = new HashSet<>();

    public void getNextStep() {
        Location nextStep = null;
        while (!forCheck.isEmpty()) {
            nextStep = findPathOrExpandNeighbors(extractedCreature,
                    forCheck.poll());
            if (nextStep != null) {
                forCheck.clear();
                checked.clear();
                if (locations.get(nextStep) != null) {
                    eatingStep(extractedCreatureLocation, nextStep);
                    damagingStep(extractedCreatureLocation, nextStep);
                } else {
                    toMove.put(extractedCreatureLocation, nextStep);
                    if (gm.checkDuplicate(extractedCreatureLocation)) {
                        System.out.println("Duplicate Grass in Location " + extractedCreatureLocation.toString());
                    }
                }
            }
        }
    }

    public StepFabric build(GameMap gm, Location current) {
        this.gm = gm;
        this.locations = gm.getWholeMapEntities();
        this.toRemove = gm.getToRemoveStorage();
        this.toMove = gm.getToMoveStorage();
        extractedCreature = (Creature) locations.get(current);
        extractedCreatureLocation = current;
        forCheck.addFirstNeighbors(getNeigborLocations(current));

        return this;
    }

    private void eatingStep(Location prev, Location next) {
        if (locations.get(prev) instanceof Eater eater
                && locations.get(next) instanceof Eatable eatable) {
            eater.eat(eatable);
            if (eater.getClass() == Herbivore.class
                    && eatable.getClass() == Grass.class) {
                toRemove.add(next);
            }
        }
    }

    private void damagingStep(Location prev, Location next) {
        if (locations.get(prev) instanceof Damager damager
                && locations.get(next) instanceof Damageble damageble) {
            damager.damage(damageble);
            if (damageble.isDead()) {
                toRemove.add(next);
            }
        }
    }

    private boolean isNeighborInField(Location neighbor) {
        return neighbor.getDx() < GameMap.FIELD_SIZE_MID.getDx()
                && neighbor.getDy() < GameMap.FIELD_SIZE_MID.getDx()
                && neighbor.getDx() >= 0
                && neighbor.getDy() >= 0;
    }

    private boolean isEatable(Location neighbor) {
        return locations.get(neighbor) instanceof Eatable;
    }

    private boolean isEmptyCell(Location neighbor) {
        return locations.get(neighbor) == null;
    }

    private ArrayList<Location> getNeigborLocations(Location startCell) {
        ArrayList<Location> correctNearestLocations = new ArrayList<>();
        Location neighbor = null;
        checked.add(startCell);

        for (var direction : Direction.values()) {
            neighbor = startCell.getNeighbor(direction.getDelta());
            if (isNeighborInField(neighbor)) {
                if (!checked.contains(neighbor) && !toMove.containsKey(neighbor)
                        && (isEatable(neighbor) || isEmptyCell(neighbor))) {
                    correctNearestLocations.add(neighbor);
                }
                checked.add(neighbor);
            }
        }

        return correctNearestLocations;
    }

    private boolean isFoodFound(Entity food,
            Class<? extends Entity> creature) {
        return food instanceof Grass && creature == Herbivore.class
                || food instanceof Herbivore && creature == Predator.class;
    }

    private Location findPathOrExpandNeighbors(Creature creature,
            Neighbor neighbor) {
        Location path = neighbor.getPathLocation();
        Location cellToSearch = neighbor.getCellToSearch();
        Entity cellClass = locations.get(cellToSearch);

        if (cellClass instanceof Eatable
                && isFoodFound(cellClass, creature.getClass())
                && !toMove.values().contains(cellToSearch)) {
            return path;
        } else {
            forCheck.addAllNeighbors(path, getNeigborLocations(cellToSearch));
        }

        return null;
    }

}
