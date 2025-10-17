package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.IRespawnable;

public class Grass extends Entity implements IEdible, IRespawnable {
    private boolean isEated;

    @Override
    public boolean isEaten() {
        return isEated;
    }

    @Override
    public void setEated() {
        isEated = true;
    }
}
