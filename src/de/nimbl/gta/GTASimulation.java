package de.nimbl.gta;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import de.nimbl.gta.math.Vec2;
import de.nimbl.gta.simulation.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GTASimulation {

    public static void main(String[] args) {
        Raylib ray = new Raylib();
        ray.core.InitWindow(800, 600, "Penis");
        ray.core.SetTargetFPS(25);

        List<Car> cars = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            int posX = ThreadLocalRandom.current().nextInt(20, 800);
            int posY = ThreadLocalRandom.current().nextInt(20, 600);
            cars.add(new Car(ray, new Vec2(posX, posY), new Vec2(20, 20)));
        }

        // Car car = new Car(ray, new Vec2(80, 80), new Vec2(20, 20));

        while(!ray.core.WindowShouldClose()) {
            ray.core.BeginDrawing();
            ray.core.ClearBackground(Color.BLACK);

            //if(ray.core.IsMouseButtonPressed(Mouse.MouseButton.MOUSE_BUTTON_LEFT)) {
            // car.follow(new Vec2(ray.core.GetMouseX(), ray.core.GetMouseY()));
            //}
            // car.update();
            // car.render();

            cars.forEach(car -> {
                car.follow(new Vec2(ray.core.GetMouseX(), ray.core.GetMouseY()));

                car.update();
                car.render();
            });


            ray.core.EndDrawing();
        }

        ray.core.CloseWindow();
    }

}
