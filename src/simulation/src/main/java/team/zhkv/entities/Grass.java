package team.zhkv.entities;

public class Grass extends Entity implements Eatable {
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
