package net.fabricmc.example;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

import java.util.logging.Logger;

public class Flyhack extends ExampleMod{
    static int toggle = 0;
    public static MinecraftClient MC = MinecraftClient.getInstance();
    int MaxSpeed = 4;
    static double FALL_SPEED = -0.042;
    double acceleration = 0.1;
    private static boolean jumpWasPressed;
    private static boolean sneakWasPressed;
    private static int tickCounter = 0;
    private static int antiKickInterval = 34;
    static double antiKickDistance = -0.06;
    static double motiony = 0.1;
    static double DownFall = 0.1;


    public static void tick(MinecraftClient client) {
        boolean jumpPressed = client.options.jumpKey.isPressed();
        boolean sneakPressed = client.options.sneakKey.isPressed();

        tickCounter++;

        if (client.player != null && ExampleMod.flyingEnabled) {
            Entity object = client.player.getRootVehicle();
            if (client.player.hasVehicle()) {
                object = client.player.getVehicle();
            }
            if (tickCounter < 2) {
                MinecraftClient client2 = MinecraftClient.getInstance();
                Vec3d velocity = client2.player.getVelocity();
                //Vec3d newVelocity = new Vec3d(velocity.x, -0.6, velocity.z);
                //object.setVelocity(newVelocity);
                ExampleMod.LOGGER.info("MoveDown");
                client2.player.setVelocity(velocity.x, -0.05, velocity.z);
            }

            if (jumpPressed && !sneakPressed){
                motiony = 0.2;
            }
            else if (jumpPressed && sneakPressed){
                //motiony = 0;
            }
            else if (!jumpPressed && sneakPressed){
                motiony = -0.2;
            }

            else{

                motiony = 0.0001;
            }

            client.player.getAbilities().flying = true;
            Vec3d velocity = object.getVelocity();
             if (tickCounter < 2) {
                 if(motiony > 0.03) {
                     DownFall = -0.14;
                 }
                 else{
                     DownFall = -0.08;
                 }
                 Vec3d newVelocity = new Vec3d(velocity.x*1.01, DownFall, velocity.z*1.01);
                //Vec3d newVelocity = new Vec3d(velocity.x, -0.6, velocity.z);
                //object.setVelocity(newVelocity);
                ExampleMod.LOGGER.info("MoveDown");
                 object.setVelocity(newVelocity);
            }
            else{
                Vec3d newVelocity = new Vec3d(velocity.x*1.01, motiony*1.01, velocity.z*1.01);
                object.setVelocity(newVelocity);
            }
            //newVelocity = new Vec3d(newVelocity.x, (toggle == 0 && newVelocity.y > FALL_SPEED) ? FALL_SPEED : newVelocity.y - FALL_SPEED-1, newVelocity.z);


            if (tickCounter >= 20 ) {
                //Vec3d newVelocity = new Vec3d(velocity.x, -FALL_SPEED, velocity.z);
                ExampleMod.LOGGER.info("CounterReset");
                tickCounter = 0;
            }

        } else {
            if (client.player != null)
                client.player.getAbilities().flying = false;
        }
    }}







