package de.nimbl.gta.math;

public class Vec2 {

    private float x;
    private float y;
    private float limit;

    public Vec2() {
        setLimit(0);
        setX(0);
        setY(0);
    }

    public Vec2(final float x, final float y) {
        setLimit(0);
        setX(x);
        setY(y);
    }

    public Vec2(final float x, final float y, final float limit) {
        setLimit(limit);
        setX(x);
        setY(y);
    }

    public boolean hasLimit() {
        return limit != 0;
    }

    public void set(final float x, final float y) {
        setX(x);
        setY(y);
    }

    public void setX(final float x) {
        if(hasLimit()) {
            this.x = Math.min(x, this.limit);
            this.x = Math.max(this.x, -this.limit);
        } else {
            this.x = x;
        }
    }

    public float getX() {
        return this.x;
    }

    public void setY(final float y) {
        if(hasLimit()) {
            this.y = Math.min(y, this.limit);
            this.y = Math.max(this.y, -this.limit);
        } else {
            this.y = y;
        }
    }

    public float getY() {
        return this.y;
    }

    public void setLimit(final float limit) {
        this.limit = limit;
    }

    public float getLimit() {
        return this.limit;
    }

    public void setMagnitude(final float amount) {
        normalize();
        mult(amount);
    }

    public float getLength() {
        return (float) Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
    }

    public void normalize() {
        float newX = getX() / getLength();
        if(Float.isNaN(newX)) {
            setX(0);
        } else {
            setX(newX);
        }

        float newY = getY() / getLength();
        if(Float.isNaN(newY)) {
            setY(0);
        } else {
            setY(newY);
        }
    }

    public void add(final Vec2 toAdd) {
        set(getX() + toAdd.getX(), getY() + toAdd.getY());
    }

    public void sub(final Vec2 toSub) {
        set(getX() - toSub.getX(), getY() - toSub.getY());
    }

    public void mult(final float toMult) {
        set(getX() * toMult, getY() * toMult);
    }

    @Override
    public String toString() {
        return "Vec2{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", limit=" + getLimit() +
                '}';
    }

}