package com.company;
import java.awt.*;

public class Hydroplane extends Plane{
    public Color dopColor;
    public boolean FrontFloat;
    public boolean SideFloat;
    public boolean BackFloat;
    private IAdding adding;





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

    public void setDopColor(Color dopColor) {
        this.dopColor = dopColor;
    }
    public IAdding getAdding() {
        return adding;
    }

    public void setAdding(IAdding adding) {
        this.adding = adding;
    }

    public Hydroplane(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean frontFloat, boolean sideFloat, boolean backFloat,int add, int number) {
        super(maxSpeed, weight, mainColor, 100, 100);
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.FrontFloat = frontFloat;
        this.SideFloat = sideFloat;
        this.BackFloat = backFloat;
    }

    public Hydroplane(String info) {
        super("");
        String[] strs = info.split(separator);
        if (strs.length == 8) {
            maxSpeed = Integer.parseInt(strs[0]);
            weight = Float.parseFloat(strs[1]);
            mainColor = Color.decode(strs[2]);
            dopColor = Color.decode(strs[3]);
            FrontFloat = Boolean.parseBoolean(strs[4]);
            SideFloat = Boolean.parseBoolean(strs[5]);
            BackFloat = Boolean.parseBoolean(strs[6]);
            if (strs[7].contains("null")) {
                adding = null;
            } else {
                String[] argsAddition = strs[7].split("\\.");
                int digit = Integer.parseInt(argsAddition[1]);
                switch (argsAddition[0]) {
                    case "FloatCircle" -> adding = new FloatCircle(digit);
                    case "FloatTriangle" -> adding = new FloatTriangle(digit);
                    case "FloatRectangle" -> adding = new FloatRectangle(digit);
                }
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(startPosX + 70, startPosY + 5, PlaneWidth - 92, PlaneHeight - 85);
        super.draw(g);
        if (adding != null) {
            adding.draw(g, startPosX, startPosY, PlaneWidth, PlaneHeight, dopColor);
        }
    }

    @Override
    public String toString() {
        return maxSpeed + separator + weight + separator + mainColor.getRGB() + separator + dopColor.getRGB() + separator
                + FrontFloat + separator + SideFloat + separator + BackFloat + separator + adding;
    }
}
