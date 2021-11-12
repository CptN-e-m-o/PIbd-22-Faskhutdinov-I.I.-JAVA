package com.company;
import java.awt.*;

public class Hydroplane extends Plane{
    public Color dopColor;
    public boolean FrontFloat;
    public boolean SideFloat;
    public boolean BackFloat;
    private Adding adding;





    public float getStartPosX() {
        return startPosX;
    }

    protected void setStartPosX(int startPosX) {
        this.startPosX = startPosX;
    }

    public boolean isFrontFloat() {
        return FrontFloat;
    }

    private void setFrontFloat(boolean FrontFloat) {
        this.FrontFloat = FrontFloat;
    }

    public boolean isSideFloat() {
        return SideFloat;
    }

    private void setSideFloat(boolean SideFloat) {
        this.SideFloat = SideFloat;
    }

    public boolean isBackFloat() {
        return BackFloat;
    }

    private void setBacKFloat(boolean BackFloat) {
        this.BackFloat = BackFloat;
    }

    public Hydroplane(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean frontFloat, boolean sideFloat, boolean backFloat,int add, int number) {
        super(maxSpeed, weight, mainColor, 100, 100);
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.FrontFloat = frontFloat;
        this.SideFloat = sideFloat;
        this.BackFloat = backFloat;
        switch (add) {
            case 0 -> adding = new FloatCircle(number);
            case 1 -> adding = new FloatRectangle(number);
            case 2 -> adding = new FloatTriangle(number);
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(startPosX + 70, startPosY + 5, PlaneWidth - 92, PlaneHeight - 85);
        super.draw(g);
        adding.draw(g, startPosX, startPosY, PlaneWidth, PlaneHeight);
    }
}
