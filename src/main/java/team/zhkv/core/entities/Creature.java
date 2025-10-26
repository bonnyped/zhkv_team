package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IEater;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.IMoveble;

/**
 * Abstract base class for all creatures in the game.
 * Implements movement, eating, and health logic.
 *
 * @author bonnyped
 */
@SuppressWarnings("unchecked")
public abstract class Creature<T extends Creature<T>>
        extends Entity implements IMoveble, IEater {
    private int hp;
    private Class<? extends IEdible> food;
    private int speed;

    /**
     * Constructs a Creature with the specified parameters.
     *
     * @param hp    initial health points
     * @param food  class of edible entities
     * @param speed movement speed
     */
    protected Creature(int hp, Class<? extends IEdible> food, int speed) {
        this.hp = hp;
        this.food = food;
        this.speed = speed;
    }

    /**
     * Returns the class of edible entities this creature can consume.
     *
     * @return the class of edible entities
     */
    public Class<? extends IEdible> getFood() {
        return food;
    }

    /**
     * Returns the current health points.
     *
     * @return the health points
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets the health points, capped at 100.
     *
     * @param hp the new health points
     */
    public void setHp(int hp) {
        this.hp = hp > 100 ? 100 : hp;
    }

    /**
     * Returns the movement speed.
     *
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the health points and returns this creature.
     *
     * @param hp the new health points
     * @return this creature
     */
    public T withHP(int hp) {
        this.hp = hp;
        return (T) this;
    }

    /**
     * Sets the class of edible entities and returns this creature.
     *
     * @param food the class of edible entities
     * @return this creature
     */
    public T withEdible(Class<? extends IEdible> food) {
        this.food = food;
        return (T) this;
    }

    /**
     * Sets the movement speed and returns this creature.
     *
     * @param speed the new speed
     * @return this creature
     */
    public T withSpeed(int speed) {
        this.speed = speed;
        return (T) this;
    }
}
