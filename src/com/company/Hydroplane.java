package com.company;
import java.awt.*;

public class Hydroplane {
    private int startPosX;
    private int startPosY;
    private int pictureWidth;
    private int pictureHeight;
    private final int hydroplaneWidth = 110;
    private final int hydroplaneHeight = 50;
    private final double changeWidth = 2.7;
    private final double changeHeight = 1.7;
    public int maxSpeed;
    public float weight;
    public Color mainColor;
    public Color dopColor;
    public boolean FrontFloat;
    public boolean SideFloat;
    public boolean BackFloat;
    private Float plane;



    public void setPosition(int x, int y, int width, int height) {
        if (x >= 0 && x + hydroplaneWidth < width && y >= 0 && y + hydroplaneHeight < height) {
            startPosX = x;
            startPosY = y;
            pictureWidth = width;
            pictureHeight = height;
        }
    }

    public void moveTrans(Direction direction) {
        float step = maxSpeed * 100 / weight;
        switch (direction) {
            case Right:
                if (startPosX + step < pictureWidth - hydroplaneWidth * changeWidth) {
                    startPosX += step;
                }
                break;
            case Left:
                if (startPosX - step > 0) {
                    startPosX -= step;
                }
                break;
            case Up:
                if (startPosY - step > 0) {
                    startPosY -= step;
                }
                break;
            case Down:
                if (startPosY + step < pictureHeight - hydroplaneHeight * changeHeight) {
                    startPosY += step;
                }
                break;
        }
    }


    public int getMaxSpeed() {
        return maxSpeed;
    }

    private void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getWeight() {
        return weight;
    }

    private void setWeight(float weight) {
        this.weight = weight;
    }

    public Color getMainColor() {
        return mainColor;
    }

    private void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }

    public Color getDopColor() {
        return dopColor;
    }

    private void setDopColor(Color dopColor) {
        this.dopColor = dopColor;
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

    private void setBackFloat(boolean BackFloat) {
        this.BackFloat = BackFloat;
    }

    public Hydroplane(int maxSpeed, float weight, Color mainColor, Color dopColor,
                      boolean frontFloat, boolean sideFloat,  boolean backFloat,int floatCount) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.FrontFloat = frontFloat;
        this.SideFloat = sideFloat;
        this.BackFloat = backFloat;
        plane = new Float();
        plane.setNumber(floatCount);
    }

    public void drawPicture(Graphics g) {
        //Корпус самолёта
        int[] hydrC_points_x = new int[5];
        hydrC_points_x[0] = startPosX + 10;
        hydrC_points_x[1] = startPosX + 90;
        hydrC_points_x[2] = startPosX + 90;
        hydrC_points_x[3] = startPosX + 10;
        hydrC_points_x[4] = startPosX + 5;
        int[] hydrC_points_y = new int[5];
        hydrC_points_y[0] = startPosY;
        hydrC_points_y[1] = startPosY;
        hydrC_points_y[2] = startPosY + 20;
        hydrC_points_y[3] = startPosY + 20;
        hydrC_points_y[4] = startPosY + 10;
        g.setColor(dopColor);
        g.fillPolygon(hydrC_points_x,hydrC_points_y, 5);
        g.setColor(mainColor);
        g.drawPolygon(hydrC_points_x, hydrC_points_y, 5);
        //Хвост самолёта
        int[] hydrB_points_x = new int[3];
        hydrB_points_x[0] = startPosX +10;
        hydrB_points_x[1] = startPosX + 30;
        hydrB_points_x[2] = startPosX +10;
        int[] hydrB_points_y = new int[3];
        hydrB_points_y[0] = startPosY;
        hydrB_points_y[1] = startPosY;
        hydrB_points_y[2] = startPosY - 20;
        g.setColor(dopColor);
        g.fillPolygon(hydrB_points_x, hydrB_points_y, 3);
        g.setColor(mainColor);
        g.drawPolygon(hydrB_points_x, hydrB_points_y, 3);
        //Кабина самолёта
        int[] hydrCockpit_points_x = new int[3];
        hydrCockpit_points_x[0] = startPosX + 90;
        hydrCockpit_points_x[1] = startPosX + 110;
        hydrCockpit_points_x[2] = startPosX + 90;
        int[] hydrCockpit_points_y = new int[3];
        hydrCockpit_points_y[0] = startPosY;
        hydrCockpit_points_y[1] = startPosY + 10;
        hydrCockpit_points_y[2] = startPosY + 20;

        g.setColor(mainColor);
        g.drawPolygon(hydrCockpit_points_x, hydrCockpit_points_y, 3);
        //Полоса в кабине
        g.setColor(mainColor);
        g.drawLine(startPosX + 90, startPosY + 10,startPosX + 110, startPosY + 10 );
        //Шасси самолёта
        g.setColor(mainColor);
        g.drawLine(startPosX + 80, startPosY + 20,startPosX + 80, startPosY + 25 );
        g.drawOval(startPosX + 79 ,startPosY + 26, 3, 3);
        g.setColor(mainColor);
        g.drawLine(startPosX + 40, startPosY + 20,startPosX + 40, startPosY + 25 );
        g.drawOval(startPosX + 36 ,startPosY + 26, 3, 3);
        g.drawOval(startPosX + 41 ,startPosY + 26, 3, 3);
        //Двигатели самолёта
        g.setColor(mainColor);
        g.fillOval(startPosX + 5 ,startPosY - 5, 18, 4);
        g.drawOval(startPosX + 5 ,startPosY - 5, 18, 4);
        //Крылья самолёта
        g.setColor(mainColor);
        g.fillOval(startPosX + 50 ,startPosY + 8, 18, 4);
        g.drawOval(startPosX + 50 ,startPosY + 8, 18, 4);
        //Отрисовка поплавков
        Float.DrawFloat(g, startPosX, startPosY, hydroplaneWidth, hydroplaneHeight, mainColor, dopColor);
    }
}
