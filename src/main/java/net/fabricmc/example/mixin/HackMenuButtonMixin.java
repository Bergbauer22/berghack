package net.fabricmc.example.mixin;

import net.fabricmc.example.GUI.HackMenuScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class HackMenuButtonMixin extends Screen {

    protected HackMenuButtonMixin(Text title) {
        super(title);
    }
    @Inject(at = @At("HEAD"),method = "initWidgets")
    private void initWidgets(CallbackInfo ci){
        this.addDrawableChild(new ButtonWidget(10, 10, 90, 20,  Text.literal("Hacks"), b ->{
            this.client.setScreen(new HackMenuScreen(this,this.client.options));
        }));

    }
}
