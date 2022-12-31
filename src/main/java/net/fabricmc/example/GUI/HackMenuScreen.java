package net.fabricmc.example.GUI;

import net.fabricmc.example.ExampleMod;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

public class HackMenuScreen extends Screen {
    private final Screen parent;
    private final GameOptions settings;
    private boolean disabled;

    public HackMenuScreen(Screen parent, GameOptions gameOptions) {
        super(Text.literal("Hack-Menu"));
        this.parent = parent;
        this.settings = gameOptions;
    }
    Text autoFishing(){
        if(ExampleMod.autoFishingEnabled){
            return Text.literal("AutoFishing: ON");

        }
        else{
            return Text.literal("AutoFishing: OFF");
        }
    }
    Text flying(){
        if(ExampleMod.flyingEnabled){
            return Text.literal("Flying: ON");

        }
        else{
            return Text.literal("Flying: OFF");
        }
    }
    private void setNormalColor() {
        setXColor(0xffffff);
        setYColor(0xffffff);
    }
    private void setGreyColor() {
        setXColor(0x808080);
        setYColor(0x808080);
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        if (ExampleMod.autoFishingEnabled) {
            setGreyColor();
        } else {
            setNormalColor();
        }

    }
    private void setYColor(int i) {
    }

    private void setXColor(int i) {
    }
    public boolean isDisabled() {
        return disabled;
    }

    protected void init(){
        this.addDrawableChild(new ButtonWidget(10, 10, 90, 20,  Text.literal("Back"), b ->{
            this.client.setScreen(this.parent);
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 25, this.height /2 +150, 135, 20,  autoFishing(), b ->{
            ExampleMod.autoFishingEnabled = !ExampleMod.autoFishingEnabled;
            b.setMessage(autoFishing());

        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + -160, this.height /2 +150, 135, 20,  Text.literal("Space-holder"), b ->{
            this.client.setScreen(this.parent);
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 25, this.height /2 +100, 135, 20,   flying(), b -> {
            ExampleMod.flyingEnabled = !ExampleMod.flyingEnabled;
            b.setMessage(flying());
        }));
    }
}


