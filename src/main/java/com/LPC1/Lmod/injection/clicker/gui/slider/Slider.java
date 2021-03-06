package com.LPC1.Lmod.injection.clicker.gui.slider;

import com.LPC1.Lmod.injection.clicker.gui.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.awt.*;

import static com.LPC1.Lmod.injection.clicker.gui.ColorUtils.getRGB_YODA;

public class Slider extends GuiButton {

    private final int minValue;
    private int currentProgress;
    public int currentValue;
    private final int iterations;
    public double guiScale;
    private boolean dragging;

    public Slider(int buttonId, int x, int y, int width, int height, String buttonText, int minValue, int iterations, double guiScale) {
        super(buttonId, x, y, width, height, buttonText);
        this.minValue = minValue;
        this.iterations = iterations;
        this.guiScale = guiScale;
    }

    @Override
    public void drawButton(Minecraft minecraft, int mouseX, int mouseY) {
        mouseX /= guiScale;
        mouseY /= guiScale;
        hovered = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
        mouseDragged(minecraft, mouseX, mouseY);
        Gui.drawRoundedThinRectangle(xPosition, yPosition, width, height, iterations, Color.WHITE.getRGB());
        Gui.drawCircleFilled(xPosition + currentProgress + 0.5, yPosition + height / 2.0, 2, 200, getRGB_YODA(255));
        currentValue = currentProgress + minValue;
        displayString = String.valueOf(currentProgress + minValue);
    }

    @Override
    public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
        mouseX /= guiScale;
        if (hovered) {
            currentProgress = mouseX - xPosition;
            dragging = true;
            return true;
        } else return false;
    }

    @Override
    protected void mouseDragged(Minecraft minecraft, int mouseX, int mouseY) {
        if (visible && dragging && hovered) currentProgress = mouseX - xPosition;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        dragging = false;
    }
}