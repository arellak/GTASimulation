package de.nimbl.gta.simulation;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import de.nimbl.gta.math.Vec2;

public class Car extends Entity {

    private Vec2 acceleration;
    private Vec2 velocity;

    private final float maxSpeed = 5.0f;
    private final float maxForce = 3.0f;

    public Car(final Raylib ray) {
        super(ray);
        setSize(new Vec2(20, 20));
        setPos(new Vec2(0, 0));

        acceleration = new Vec2(0, 0);
        velocity = new Vec2(0, 0);
    }

    public void follow(final Vec2 position) {
        Vec2 way = new Vec2(position.getX(), position.getY());
        way.sub(getPos());
        way.setMagnitude(maxSpeed);

        Vec2 push = new Vec2(way.getX(), way.getY(), maxForce);
        push.sub(velocity);
        push(push);
    }

    public void push(final Vec2 push) {
        acceleration.add(push);
    }

    @Override
    public void update() {
        velocity.add(acceleration);
        velocity.setLimit(maxSpeed);

        getPos().add(velocity);
        setPos(this.getPos());
        acceleration.set(0, 0);
    }

    @Override
    public void render() {
        ray.shapes.DrawCircle((int) (getPos().getX()), (int) (getPos().getY()), (int) getSize().getX(), Color.DARKGREEN);
    }
}
