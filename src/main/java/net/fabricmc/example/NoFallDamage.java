package net.fabricmc.example;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;


public class NoFallDamage{
    public static MinecraftClient MC = MinecraftClient.getInstance();
    private static boolean isFallingFastEnoughToCauseDamage(ClientPlayerEntity player)
    {
        return player.getVelocity().y < -0.9;
    }
    public static void tick(){
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        if(client.player == null){
            return;
        }
        float fallDistance = player.fallDistance;
        if (!ExampleMod.noFallDamage){
            return;
        }
        if(fallDistance <= (player.isFallFlying() ? 1 : 2)){
            return;
        }


        player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
    }

}
