package de.nimbl.gta;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.input.Mouse;
import de.nimbl.gta.math.Vec2;
import de.nimbl.gta.simulation.Car;

public class GTASimulation {

    public static void main(String[] args) {
        Raylib ray = new Raylib();
        ray.core.InitWindow(800, 600, "Penis");
        ray.core.SetTargetFPS(30);
        Car car = new Car(ray);

        while(!ray.core.WindowShouldClose()) {
            ray.core.BeginDrawing();
            ray.core.ClearBackground(Color.BLACK);

            //if(ray.core.IsMouseButtonPressed(Mouse.MouseButton.MOUSE_BUTTON_LEFT)) {
            car.follow(new Vec2(ray.core.GetMouseX(), ray.core.GetMouseY()));
            //}
            car.update();
            car.render();

            ray.core.EndDrawing();
        }

        ray.core.CloseWindow();
    }

}
