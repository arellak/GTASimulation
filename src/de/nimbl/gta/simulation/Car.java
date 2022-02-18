package de.nimbl.gta.simulation;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import de.nimbl.gta.math.Vec2;

public class Car extends Entity {

    private Vec2 acceleration;
    private Vec2 velocity;

    private float maxSpeed;

    public Car(final Raylib ray) {
        super(ray);
        setPos(new Vec2(100, 100));
        setSize(new Vec2(20, 20));

        this.acceleration = new Vec2(0, 0);
        this.velocity = new Vec2(0, 0);
        this.maxSpeed = 1f;
    }

    public void follow(final Vec2 position) {
        Vec2 way = new Vec2(position.getX(), position.getY());
        way.sub(getPos());
        way.setMagnitude(maxSpeed);

        Vec2 push = new Vec2(way.getX(), way.getY());
        push.sub(velocity);
        push.setLimit(maxSpeed);
        push(push);
    }

    public void push(final Vec2 push) {
        acceleration = new Vec2(push.getX(), push.getY());
        acceleration.add(acceleration);
    }

    @Override
    public void update() {
        velocity = new Vec2();
        velocity.add(acceleration);

        Vec2 newPos = new Vec2(getPos().getX(), getPos().getY());
        newPos.add(velocity);
        newPos.setLimit(maxSpeed);
        setPos(newPos);
        acceleration.set(0, 0);
    }

    @Override
    public void render() {
        ray.shapes.DrawRectangle((int) getPos().getX(), (int) getPos().getY(), (int) getSize().getX(), (int) getSize().getY(), Color.BLACK);
    }
}
