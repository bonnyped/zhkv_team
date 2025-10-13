package team.zhkv.entities;

import java.util.List;

import team.zhkv.GameMap;
import team.zhkv.move.BFS;
import team.zhkv.move.Coordinate;
import team.zhkv.service.impl.IEater;
import team.zhkv.service.impl.IMoveble;

public abstract class Creature extends Entity implements IMoveble, IEater {
    protected Class<? extends Entity> food;
    protected boolean isEated;

    protected int hp = 100;

    protected int speed;

    @Override
    public Coordinate makeMove(GameMap gm, Coordinate oldLocation) {
        List<Coordinate> path = new BFS().build(gm, oldLocation, food)
                .getPath();

        return path.isEmpty() ? null : applySpeedToPath(gm, path);
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
