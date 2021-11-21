package edu.purdue.model;

public class SpecialFood extends Food {

    public enum FoodType {
        DOUBLE_SCORE, REDUCE_LENGTH, SLOW_DOWN
    }

    private int timeBeforeDisappear;
    private boolean visible;
    private FoodType type;

    public SpecialFood() {
        super();
        timeBeforeDisappear = 0;
        visible = false;
    }

    public int getTimeBeforeDisappear() {
        return timeBeforeDisappear;
    }

    public void setTimeBeforeDisappear(int timeBeforeDisappear) {
        this.timeBeforeDisappear = timeBeforeDisappear;
    }

    public void decrementTimeBeforeDisappear() {
        this.timeBeforeDisappear -= 1;
        if (timeBeforeDisappear < 0) {
            this.timeBeforeDisappear = 0;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }
}
