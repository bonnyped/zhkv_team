package team.zhkv.entities;

import java.time.LocalDate;
import java.util.List;

import team.zhkv.move.Location;
import team.zhkv.render.GameMap;

public abstract class Creature extends Entity implements Moveble, Eater {
    protected Class<? extends Entity> food;
    protected boolean isEated;

    protected int hp = 100;

    protected int speed;

    @Override
    public Location makeMove(GameMap gm, Location oldLocation) {
        List<Location> path = new BFS().build(gm, oldLocation, food)
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

    private Location applySpeedToPath(GameMap gm, List<Location> path) {
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
