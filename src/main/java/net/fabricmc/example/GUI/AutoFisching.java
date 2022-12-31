package net.fabricmc.example.GUI;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;



public class AutoFisching {
    public static int AngelEinhohlen = -1;
    private static MinecraftClient client;

    public void tsick(MinecraftClient client){
        if(AngelEinhohlen > 0){
            AngelEinhohlen--;
        }
        if(AngelEinhohlen== 0 && ExampleMod.autoFishingEnabled && isHoldingFishingRod()){
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            AngelEinhohlen = -1;
        }
    }
    private static boolean isItemFishingRod(Item item) {
        return item == Items.FISHING_ROD;
    }
    private static ItemStack getHeldItem() {
        return client.player.getMainHandStack();
    }

    public static boolean isHoldingFishingRod() {
        return isItemFishingRod(getHeldItem().getItem());
    }
    public void setRecastRod(int coundown){
        AngelEinhohlen = coundown;
    }
}
