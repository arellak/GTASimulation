package de.nimbl.gta;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import de.nimbl.gta.math.Vec2;
import de.nimbl.gta.simulation.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GTASimulation {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static void main(String[] args) {
        Raylib ray = new Raylib();
        ray.core.InitWindow(WIDTH, HEIGHT, "Penis");
        ray.core.SetTargetFPS(25);

        Car player = new Car(ray, new Vec2(WIDTH/2, HEIGHT/2), new Vec2(20, 20));
        player.setMaxSpeed(8);
        player.setMaxForce(0.4f);

        List<Car> cars = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            int posX = ThreadLocalRandom.current().nextInt(50, 800);
            int posY = ThreadLocalRandom.current().nextInt(50, 600);
            Car car = new Car(ray, new Vec2(posX, posY), new Vec2(10, 10));
            car.setMaxSpeed(ThreadLocalRandom.current().nextFloat(3, 6));
            cars.add(car);
        }

        while(!ray.core.WindowShouldClose()) {
            ray.core.BeginDrawing();
            ray.core.ClearBackground(Color.BLACK);

            cars.forEach(car -> {
                car.follow(player.getPos());
                car.update();
                car.render();
            });

            // player.follow(new Vec2(ray.core.GetMouseX(), ray.core.GetMouseY()));
            player.flee(cars);
            player.update();
            player.render();

            ray.core.EndDrawing();
        }

        ray.core.CloseWindow();
    }

}
