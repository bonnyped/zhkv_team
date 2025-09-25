package team.zhkv.entities;

import java.util.Set;

import team.zhkv.render.GameMap;
import team.zhkv.render.Location;

public class Predator extends Creature {
    private int damage = 50;

    @Override
    public void makeMove(GameMap gm,
            Location creatureLocation, Set<Location> newCreaturesLocations) {

    }
}
