package de.nimbl.gta.simulation;

import com.raylib.java.Raylib;
import de.nimbl.gta.math.Vec2;

public abstract class Entity implements IRenderable{

    protected Raylib ray;

    private Vec2 pos;
    private Vec2 size;

    public Entity(Raylib ray) {
        this.ray = ray;
    }

    public abstract void render();
    public abstract void update();

    public void setPos(Vec2 pos) {
        this.pos = pos;
    }

    public void setPos(float x, float y) {
        this.pos = new Vec2(x, y);
    }

    public Vec2 getPos() {
        return this.pos;
    }

    public void setSize(Vec2 size) {
        this.size = size;
    }

    public void setSize(float width, float height) {
        this.size = new Vec2(width, height);
    }

    public Vec2 getSize() {
        return this.size;
    }

}
