package net.fabricmc.example.mixin;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.GUI.AutoFisching;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Mixin(FishingBobberEntity.class)
public abstract class AutoFishingMixin {
    @Shadow private boolean caughtFish;

    @Inject(method = "onTrackedDataSet",at = @At("TAIL"))
    public void onTrackedDataSet(TrackedData<?> data, CallbackInfo ci){
        MinecraftClient client = MinecraftClient.getInstance();

        if(caughtFish && ExampleMod.autoFishingEnabled ){

            //ExampleMod.getInstance().getAutoFisching().setRecastRod(20);

            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    // Dieser Code wird in 1 Sekunde ausgeführt.
                    client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
                }
            }, 1, TimeUnit.SECONDS);


        }
    }
    private static MinecraftClient client;
    private static boolean isItemFishingRod(Item item) {
        return item == Items.FISHING_ROD || item instanceof FishingRodItem;
    }
    private static ItemStack getHeldItem() {
        assert client.player != null;
        return client.player.getMainHandStack();
    }

    private static boolean isHoldingFishingRod_() {
        return isItemFishingRod(getHeldItem().getItem());
    }
}
