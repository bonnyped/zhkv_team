package team.zhkv.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import team.zhkv.App;
import team.zhkv.entities.Creature;
import team.zhkv.entities.Eatable;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Predator;
import team.zhkv.render.Direction;
import team.zhkv.render.Location;

public class BFS {
    private Map<Location, Entity> locations;
    private NeighborsDeque forCheck = new NeighborsDeque();
    private HashSet<Location> checked = new HashSet<>();

    public BFS(Map<Location, Entity> locations) {
        this.locations = locations;
    }

    private boolean isNeighborInField(Location neighbor) {
        return neighbor.getDx() < App.FIELD_SIZE.getDx()
                && neighbor.getDy() < App.FIELD_SIZE.getDx()
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

    private Location findPathOrExpandNeighbors(Creature creature, Neighbor neighbor) {
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

    public Location generateCreaturesStep(Location creature) {
        Creature extractedCreature = (Creature) locations.get(creature);
        Location nextStep = null;

        forCheck.addFirstNeighbors(getNeigborLocations(creature));

        while (!forCheck.isEmpty() && nextStep == null) {
            nextStep = findPathOrExpandNeighbors(extractedCreature, forCheck.poll());
        }

        return nextStep;
    }
}
