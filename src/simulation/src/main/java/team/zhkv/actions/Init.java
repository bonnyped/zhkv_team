package team.zhkv.actions;

public abstract class Init<T> implements Action<T> {
    public abstract void init(T object);
}
