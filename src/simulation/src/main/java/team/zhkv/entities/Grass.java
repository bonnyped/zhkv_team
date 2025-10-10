package team.zhkv.entities;

import team.zhkv.service.impl.Edible;

public class Grass extends Entity implements Edible {
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
