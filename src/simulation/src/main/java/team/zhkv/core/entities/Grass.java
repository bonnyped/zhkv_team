package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.Respawnable;

public class Grass extends Entity implements IEdible, Respawnable {
    private boolean isEated;

    @Override
    public boolean isEated() {
        return isEated;
    }

    @Override
    public void setEated() {
        isEated = true;
    }
}
