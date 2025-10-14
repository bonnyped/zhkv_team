package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.Respawnable;

public class Grass extends Entity implements IEdible, Respawnable {
    private boolean isEated = false;

    @Override
    public boolean isEated() {
        setEated();
        return isEated;
    }

    @Override
    public void respawn() {

    }

    private void setEated() {
        isEated = true;
    }

}
