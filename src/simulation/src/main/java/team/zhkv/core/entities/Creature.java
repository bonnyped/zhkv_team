package team.zhkv.core.entities;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import team.zhkv.map.GameMap;
import team.zhkv.actions.move.Pathfinder;
import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.interfaces.IEater;
import team.zhkv.core.interfaces.IMoveble;

public abstract class Creature extends Entity implements IMoveble, IEater {
    private int hp;
    private Class<? extends Entity> food;
    private int speed;
    private boolean isEated;
    private Pathfinder pathfinder;

    protected Creature(int hp, Class<? extends Entity> food, int speed) {
        this.hp = hp;
        this.food = food;
        this.speed = speed;
        pathfinder = new Pathfinder();
    }

    @Override
    public void makeMove(GameMap gm, Entry<Coordinate, List<Coordinate>> entry) {
        var entities = gm.getEntities();
        if(/* target == empty */) {

        } else if(/* target == food && food == edable for creature && src ! nearest negbor target */) {
        } else if(/*  */) {
            
        }
        }

    public List<Coordinate> findPath(GameMap gm, Coordinate oldLocation) {
        pathfinder.build(gm, oldLocation, food);
        return pathfinder.getPath();
    }

    public Class<? extends Entity> getFood() {
        return food;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public Coordinate applySpeedToPath(List<Coordinate> path) {
        return path.stream()
                .limit(speed)
                .reduce((a, b) -> b)
                .orElse(null);
    }

    private Coordinate applySpeedToPath(GameMap gm, List<Coordinate> path) {
        if (isSpeedOrPathSizeEqualsOneCell(path.size())) {
            return path.get(0);
        } else if (isSpeedGreaterThenOneCell()
                && isTargetFood(gm.getEntity(path.get(speed - 1)))) {
            return path.get(speed - 2);
        } else {
            return path.get(speed - 1);
        }
    }

    private boolean isSpeedOrPathSizeEqualsOneCell(int size) {
        return speed == 1 || size == 1;
    }

    private boolean isSpeedGreaterThenOneCell() {
        return speed > 1;
    }

    private boolean isTargetFood(Entity target) {
        return target != null && target.getClass() == food;
    }
}
