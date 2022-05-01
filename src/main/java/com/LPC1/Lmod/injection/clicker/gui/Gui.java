package com.LPC1.Lmod.injection.clicker.gui;

import com.LPC1.Lmod.injection.clicker.gui.customfont.FontUtil;
import com.LPC1.Lmod.injection.clicker.gui.slider.Slider;
import com.LPC1.Lmod.injection.setup.Setup;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import static com.LPC1.Lmod.injection.clicker.gui.ColorUtils.getRGB_YODA;
import static java.lang.Math.PI;
import static org.lwjgl.opengl.GL11.*;

public class Gui extends GuiScreen {

    private final ResourceLocation LogoPng = new ResourceLocation("logo_final.png");
    private final Setup setup;

    private int YodaColors = 20 + 55 + 190;

    protected int GuiWidth = 250, GuiHeight = 125;
    protected int GuiX, GuiY;

    private static Tessellator tessellator;
    private static WorldRenderer worldRenderer;

    private double guiScale;

    private List<Slider> SliderList = new ArrayList<Slider>();

    {
        tessellator = Tessellator.getInstance();
        worldRenderer = tessellator.getWorldRenderer();
    }

    public Gui(Setup setup) {
        this.setup = setup;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float PartialTicks) {

        drawDefaultBackground();

        GlStateManager.scale(guiScale, guiScale, guiScale);

        super.drawScreen(mouseX, mouseY, PartialTicks);

        drawRectangle(GuiX, GuiY, GuiWidth, GuiHeight, 80, 80, 86, 255);

        drawLine(GuiX, GuiY, 0, GuiHeight, 2, 20, 55, 190, 255);
        drawLine(GuiX, GuiY, GuiWidth, 0, 2, 20, 55, 190, 255);
        drawLine(GuiX + GuiWidth, GuiY, 0, GuiHeight, 2, 20, 55, 190, 255);
        drawLine(GuiX, GuiY + GuiHeight, GuiWidth, 0, 2, 20, 55, 190, 255);


        mc.renderEngine.bindTexture(LogoPng);
        drawTexture(GuiX + 8,GuiY + 8, 22, 22, 0,0,1,1);


        FontUtil.title.drawString("AutoClicker V1.0", GuiX + 40, GuiY + 15, getRGB_YODA(255));

        FontUtil.normal.drawString("Max CPS : ", GuiX + 40, GuiY + 45, getRGB_YODA(255));
        FontUtil.normal.drawString("Min CPS : ", GuiX + 40, GuiY + 70, getRGB_YODA(255));

        for (Slider slider : this.SliderList) {
            slider.drawButton(mc, mouseX, mouseY);
        }

    }

    @Override
    public void initGui() {

        super.initGui();

        SliderList.clear();

        guiScale = width / 480.0;

        GuiX = (int) ((width / guiScale - GuiWidth) / 2);
        GuiY = (int) ((height / guiScale - GuiHeight) / 2);

        System.out.println(width);
        System.out.println(height);

        SliderList.add(new Slider(1,GuiX + 12, GuiY + 55, GuiY + 60, 100, "su", 5, 10, guiScale));
        SliderList.add(new Slider(2,GuiX + 12, GuiY + 80, GuiY + 85, 100, "su", 5, 10, guiScale));
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public void drawRectangle(double x1, double y1, double x2, double y2, int red, int green, int blue, int alpha) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        worldRenderer.begin(GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(x1, y1, 0).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(x1, y1 + y2, 0).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(x1 + x2, y1 + y2, 0).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(x1 + x2, y1, 0).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public void drawLine(double x1, double y1, double x2, double y2, float lineWidth, int red, int green, int blue, int alpha) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glLineWidth(lineWidth);
        worldRenderer.begin(GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(x1, y1, 0).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(x1 + x2, y1 + y2, 0).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public void drawTextureColor(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, int red, int green, int blue, int alpha) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        worldRenderer.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldRenderer.pos(x1, y1, 0).tex(x3, y3).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(x1, y1 + y2, 0).tex(x3, y3 + y4).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(x1 + x2, y1 + y2, 0).tex(x3 + x4, y3 + y4).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(x1 + x2, y1, 0).tex(x3 + x4, y3).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
    }

    public static void drawTexture(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        worldRenderer.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(x1, y1, 0).tex(x3, y3).endVertex();
        worldRenderer.pos(x1, y1 + y2, 0).tex(x3, y3 + y4).endVertex();
        worldRenderer.pos(x1 + x2, y1 + y2, 0).tex(x3 + x4, y3 + y4).endVertex();
        worldRenderer.pos(x1 + x2, y1, 0).tex(x3 + x4, y3).endVertex();
        tessellator.draw();
    }

    public static void drawCircleFilled(double x1, double y1, double radius, int iterations, int color) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.color((float) (color >> 16 & 255) / 255f, (float) (color >> 8 & 255) / 255f, (float) (color & 255) / 255f, (float) (color >> 24 & 255) / 255f);
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_POLYGON_SMOOTH);
        worldRenderer.begin(GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
        for (int i = 0; i < iterations; i++) {
            double angle = PI * 2 * i / iterations;
            worldRenderer.pos(x1 + Math.sin(angle) * radius, y1 + Math.cos(angle) * radius, 0).endVertex();
        }
        tessellator.draw();
        glDisable(GL_POLYGON_SMOOTH);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();

    }

    public static void drawRoundedThinRectangle(int x1, int y1, int width, int height, int iterations, int color) {
        int radius = height / 2;
        int circleDrawPoint = y1 + radius;
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color((float) (color >> 16 & 255) / 255f, (float) (color >> 8 & 255) / 255f, (float) (color & 255) / 255f, (float) (color >> 24 & 255) / 255f);
        glEnable(GL_POLYGON_SMOOTH);
        worldRenderer.begin(GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x1 + radius, y1, 0).endVertex();
        for (int i = 0; i < iterations; i++) {
            double angle = PI * i / iterations;
            worldRenderer.pos(x1 + width - radius + Math.sin(angle) * radius, circleDrawPoint + Math.cos(angle) * radius, 0).endVertex();
        }
        tessellator.draw();
        worldRenderer.begin(GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x1 + width - radius, y1 + height, 0).endVertex();
        for (int i = 0; i < iterations; i++) {
            double angle = - PI * i / iterations;
            worldRenderer.pos(x1 + radius + Math.sin(angle) * radius, circleDrawPoint - Math.cos(angle) * radius, 0).endVertex();
        }
        tessellator.draw();
        glDisable(GL_POLYGON_SMOOTH);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

}


