package cs5004.animator.model.buildersrc;

public abstract class Trans {
    protected String name;

    public String getName() {
        return name;
    }

    public Trans(String name) {
        this.name = name;
    }
}
