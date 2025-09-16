package team.zhkv.utils;

import java.util.Objects;

public class PropertiesStorage {
    private int sizex = 10;
    private int sizey = 10;
    private int maxEntitiesCount = sizex * sizey / 2;
    private int minHerbivorePopulation = maxEntitiesCount / 7;
    private int minGrassQuantity = minHerbivorePopulation;
    private int rocksCount = minGrassQuantity;
    private int treesCount = rocksCount;
    private int predatorsCount = maxEntitiesCount / 5;

    private int grassCount = minGrassQuantity * 2;

    private int herbivoreCount = minHerbivorePopulation * 2;

    public int getSizex() {
        return this.sizex;
    }

    public int getSizey() {
        return this.sizey;
    }

    public int getMaxEntitiesCount() {
        return this.maxEntitiesCount;
    }

    public int getMinHerbivorePopulation() {
        return this.minHerbivorePopulation;
    }

    public int getMinGrassQuantity() {
        return this.minGrassQuantity;
    }

    public int getRocksCount() {
        return this.rocksCount;
    }

    public int getTreesCount() {
        return this.treesCount;
    }

    public int getPredatorsCount() {
        return this.predatorsCount;
    }

    public int getGrassCount() {
        return this.grassCount;
    }

    public int getHerbivoreCount() {
        return this.herbivoreCount;
    }

    public void setGrassCount(int grassCount) {
        this.grassCount = grassCount;
    }

    public void setHerbivoreCount(int herbivoreCount) {
        this.herbivoreCount = herbivoreCount;
    }

    public PropertiesStorage grassCount(int grassCount) {
        setGrassCount(grassCount);
        return this;
    }

    public PropertiesStorage herbivoreCount(int herbivoreCount) {
        setHerbivoreCount(herbivoreCount);
        return this;
    }
}
