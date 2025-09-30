package team.zhkv.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import team.zhkv.App;
import team.zhkv.move.Direction;
import team.zhkv.move.Location;
import team.zhkv.render.ChangeStorage;
import team.zhkv.utils.Neighbor;
import team.zhkv.utils.NeighborsDeque;

public class StepFabric {
    private Map<Location, Entity> locations;
    private Map<Location, Location> toMove;
    private Set<Location> toRemove;
    private Map<Location, Location> toDamage;
    private Map<Location, Location> toEat;
    private Creature extractedCreature;
    private Location extractedCreatureLocation;
    private NeighborsDeque forCheck = new NeighborsDeque();
    private HashSet<Location> checked = new HashSet<>();

    public void getNextStep() {
        Location nextStep;
        while (!forCheck.isEmpty()) {
            nextStep = findPathOrExpandNeighbors(extractedCreature,
                    forCheck.poll());
            if (nextStep != null) {
                addEatActions(extractedCreatureLocation, nextStep);
                addDamageActions(extractedCreatureLocation, nextStep);
                addMoveActions(extractedCreatureLocation, nextStep);
            }
        }
    }

    public StepFabric build(Map<Location, Entity> locations,
            Location current, ChangeStorage cs) {
        forCheck.clear();
        checked.clear();
        this.locations = locations;
        extractedCreature = (Creature) locations.get(current);
        extractedCreatureLocation = current;
        this.toMove = cs.getToMove();
        this.toRemove = cs.getToRemove();
        this.toDamage = cs.getToDamage();
        this.toEat = cs.getToEat();
        forCheck.addFirstNeighbors(getNeigborLocations(current));

        return this;
    }

    private void addEatActions(Location prev, Location next) {
        Entity activeEntity = locations.get(prev);
        Entity passiveEntityCell = locations.get(next);

        if (activeEntity instanceof Eater
                && passiveEntityCell instanceof Eatable) {
            toEat.put(prev, next);
            if (activeEntity.getClass() == Herbivore.class
                    && passiveEntityCell.getClass() == Grass.class) {
                toRemove.add(next);
            }
        }
    }

    private void addDamageActions(Location prev, Location next) {
        if (locations.get(prev) instanceof Damager
                && locations.get(next) instanceof Damageble) {
            toDamage.put(prev, next);
        }
    }

    private void addMoveActions(Location prev, Location next) {
        if (locations.get(next) == null) {
            toMove.put(prev, next);
        }
    }

    private boolean isNeighborInField(Location neighbor) {
        return neighbor.getDx() < App.FIELD_SIZE_MIN.getDx()
                && neighbor.getDy() < App.FIELD_SIZE_MIN.getDx()
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
                if (!checked.contains(neighbor)
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
                && isFoodFound(cellClass, creature.getClass())) {
            return path;
        } else {
            forCheck.addAllNeighbors(path, getNeigborLocations(cellToSearch));
        }

        return null;
    }

}
