package team.zhkv.entities;

public class Grass extends Entity implements Eatable {
    private boolean isEated = false;

    @Override
    public boolean isEated() {
        return isEated;
    }

    public void setEated() {
        isEated = true;
    }

}
