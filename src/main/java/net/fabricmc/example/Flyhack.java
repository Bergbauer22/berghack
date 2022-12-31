package net.fabricmc.example;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class Flyhack {
    int toggle = 0;
    int MaxSpeed = 4;
    double FALL_SPEED = -0.039;
    double acceleration = 0.1;
    public static void tick(MinecraftClient client){
        if(client.player!=null && ExampleMod.flyingEnabled){
            client.player.getAbilities().flying = true;
            /*boolean jumpPressed = client.options.jumpKey.isPressed();
            boolean forwardPressed = client.options.forwardKey.isPressed();
            boolean leftPressed = client.options.leftKey.isPressed();
            boolean rightPressed = client.options.rightKey.isPressed();
            boolean backPressed = client.options.backKey.isPressed();*/
            }
        else {
            if (client.player!=null)
            client.player.getAbilities().flying = false;
        }
            /*
            Entity object = client.player.getRootVehicle();
            if (client.player.hasVehicle()){
                object = client.player.getVehicle();
            }
            Vec3d velocity = object.getVelocity();
            //Vec3d newVelocity = new Vec3d(velocity.x, velocity.y- FALL_SPEED) ? FALL_SPEED : newVelocity.y - FALL_SPEED, newVelocity.z);*/
    }
}
