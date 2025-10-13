package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IEdible;

public class Grass extends Entity implements IEdible {
    private boolean isEated = false;

    @Override
    public boolean isEated() {
        setEated();
        return isEated;
    }

    private void setEated() {
        isEated = true;
    }

}
