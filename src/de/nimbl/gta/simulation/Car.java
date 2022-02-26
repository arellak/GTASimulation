package de.nimbl.gta.simulation;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.input.Keyboard;
import de.nimbl.gta.math.Vec2;

import com.raylib.java.core.rCore;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Car extends Entity {

    private Vec2 acceleration;
    private Vec2 velocity;

    private float maxSpeed = 7.5f;
    private float maxForce = 0.7f;

    public Color color;
    private Color oldColor;

    private float nearestDistance;

    public Car(Raylib ray) {
        super(ray);
        setSize(new Vec2(20, 20));
        setPos(new Vec2(0, 0));

        acceleration = new Vec2(0, 0);
        velocity = new Vec2(0, 0);
        this.color = randomColor();
        this.oldColor = color;
    }

    public Car(Raylib ray, Vec2 pos, Vec2 size) {
        super(ray);
        setSize(size);
        setPos(pos);

        acceleration = new Vec2(0, 0);
        velocity = new Vec2(0, 0);
        this.color = randomColor();
        this.oldColor = color;
    }

    public void follow(Vec2 position) {
        Vec2 way = new Vec2(position.getX(), position.getY());
        way.sub(getPos());
        way.setMagnitude(maxSpeed);

        Vec2 push = new Vec2(way.getX(), way.getY());
        push.setLimit(maxForce);
        push.sub(velocity);
        push(push);
    }

    public void push(Vec2 push) {
        acceleration.add(push);
    }

    public void move() {
        if(rCore.IsKeyDown(Keyboard.KEY_D)) {
            follow(new Vec2(getPos().getX()+maxSpeed*5, getPos().getY()));
        } else if(rCore.IsKeyDown(Keyboard.KEY_A)) {
            follow(new Vec2(getPos().getX()-maxSpeed*5, getPos().getY()));
        }

        if(rCore.IsKeyDown(Keyboard.KEY_W)) {
            follow(new Vec2(getPos().getX(), getPos().getY()-maxSpeed*5));
        } else if(rCore.IsKeyDown(Keyboard.KEY_S)) {
            follow(new Vec2(getPos().getX(), getPos().getY()+maxSpeed*5));
        }
    }

    @Override
    public void update() {
        velocity.add(acceleration);
        velocity.setLimit(maxSpeed);

        getPos().add(velocity);
        setPos(this.getPos());
        acceleration.set(0, 0);

        checkCollisions();
    }

    public void flee(List<Car> cars) {
        Car nearest = cars.get(getNearestEntity(cars));
        if(nearestDistance > 0) {
            System.out.println(nearest.getPos().multiplicate(-1));
            follow(nearest.getPos().multiplicate(-1));
        }
    }

    public void checkCollisions() {
        if (getPos().getX() > 800 - this.getSize().getX()) {
            getPos().setX(this.getSize().getX());
        }

        if (getPos().getX() < this.getSize().getX()/2) {
            getPos().setX(800 - this.getSize().getX());
        }


        if (getPos().getY() < this.getSize().getY()/2) {
            getPos().setY(600 - this.getSize().getY());
        }

        if (getPos().getY() > 600 - this.getSize().getY()) {
            getPos().setY(this.getSize().getY());
        }
    }

    @Override
    public void render() {
        if(this.oldColor == this.color) {
            // System.out.println("new");
            ray.shapes.DrawCircle((int) (getPos().getX()), (int) (getPos().getY()), (int) getSize().getX(), this.color);
        } else {
            //System.out.println("old");
            ray.shapes.DrawCircle((int) (getPos().getX()), (int) (getPos().getY()), (int) getSize().getX(), this.oldColor);
        }
    }

    public int getNearestEntity(List<Car> cars) {
        if(cars.isEmpty()) return -1;

        int nearestIndex = 0;
        Car nearestCar = cars.get(0);
        Vec2 nearestDistance = this.getPos().subtract(nearestCar.getPos());

        float currentNearestX = Math.abs(nearestDistance.getX());
        float currentNearestY = Math.abs(nearestDistance.getY());
        float currentNearestSum = currentNearestX + currentNearestY;

        this.nearestDistance = currentNearestSum;

        for(int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            Vec2 distance = this.getPos().subtract(car.getPos());

            float distanceX = Math.abs(distance.getX());
            float distanceY = Math.abs(distance.getY());
            float distanceSum = distanceX + distanceY;

            currentNearestX = Math.abs(nearestDistance.getX());
            currentNearestY = Math.abs(nearestDistance.getY());
            currentNearestSum = currentNearestX + currentNearestY;

            if(distanceSum < currentNearestSum) {
                nearestCar = car;
                nearestDistance = this.getPos().subtract(nearestCar.getPos());
                nearestIndex = i;
                this.nearestDistance = currentNearestSum;
            }
        }
        return nearestIndex;
    }

    public Color randomColor() {
        Color random = new Color();
        random.setR(ThreadLocalRandom.current().nextInt(255));
        random.setG(ThreadLocalRandom.current().nextInt(255));
        random.setB(ThreadLocalRandom.current().nextInt(255));
        return random;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxForce(float maxForce) {
        this.maxForce = maxForce;
    }

    public float getMaxForce() {
        return this.maxForce;
    }

}