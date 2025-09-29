package team.zhkv.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import team.zhkv.App;
import team.zhkv.render.Direction;
import team.zhkv.render.Location;
import team.zhkv.utils.Neighbor;
import team.zhkv.utils.NeighborsDeque;

public class StepFabric {
    private Map<Location, Entity> oldLocations;
    private Map<Location, Entity> entitiesToRemove;
    private Map<Location, Entity> newCreaturesLocations;
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
                newCreaturesLocations.put(nextStep, extractedCreature);
                entitiesToRemove.put(extractedCreatureLocation,
                        extractedCreature);
            }
        }
    }

    public StepFabric build(Map<Location, Entity> oldLocations,
            Location current, Map<Location, Entity> entitiesToRemove,
            Map<Location, Entity> newCreaturesLocations) {
        forCheck.clear();
        checked.clear();
        this.oldLocations = oldLocations;
        this.entitiesToRemove = entitiesToRemove;
        this.newCreaturesLocations = newCreaturesLocations;
        forCheck.addFirstNeighbors(getNeigborLocations(current));
        extractedCreature = (Creature) oldLocations.get(current);
        extractedCreatureLocation = current;

        return this;
    }

    private boolean isNeighborInField(Location neighbor) {
        return neighbor.getDx() < App.FIELD_SIZE_MIN.getDx()
                && neighbor.getDy() < App.FIELD_SIZE_MIN.getDx()
                && neighbor.getDx() >= 0
                && neighbor.getDy() >= 0;
    }

    private boolean isEatable(Location neighbor) {
        return oldLocations.get(neighbor) instanceof Eatable;
    }

    private boolean isEmptyCell(Location neighbor) {
        return oldLocations.get(neighbor) == null;
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

    private Location findPathOrExpandNeighbors(Creature creature, Neighbor neighbor) {
        Location path = neighbor.getPathLocation();
        Location cellToSearch = neighbor.getCellToSearch();
        Entity cellClass = oldLocations.get(cellToSearch);

        if (cellClass instanceof Eatable
                && isFoodFound(cellClass, creature.getClass())) {
            return path;
        } else {
            forCheck.addAllNeighbors(path, getNeigborLocations(cellToSearch));
        }

        return null;
    }

}
