package com.company;
import java.awt.*;

public class Plane extends Vehicle {
    protected int PlaneWidth = 100;
    protected int PlaneHeight = 100;
    protected double changeWidth = 3.1;
    protected double changeHeight = 2.1;
    protected String separator = ";";

    public Plane(int maxSpeed, float weight, Color mainColor) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
    }

    protected Plane(int maxSpeed, float weight, Color mainColor, int planeWidth, int planeHeight) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.PlaneWidth = planeWidth;
        this.PlaneHeight = planeHeight;
    }

    public Plane(String info) {
        String[] strs = info.split(separator);
        if (strs.length == 3) {
            maxSpeed = Integer.parseInt(strs[0]);
            weight = Float.parseFloat(strs[1]);
            mainColor = Color.decode(strs[2]);
        }
    }

    @Override
    public void draw(Graphics g) {
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
        g.setColor(mainColor);
        g.fillPolygon(hydrC_points_x,hydrC_points_y, 5);
        g.setColor(Color.BLACK);
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
        g.setColor(mainColor);
        g.fillPolygon(hydrB_points_x, hydrB_points_y, 3);
        g.setColor(Color.BLACK);
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

        g.setColor(Color.BLACK);
        g.drawPolygon(hydrCockpit_points_x, hydrCockpit_points_y, 3);
        //Полоса в кабине
        g.setColor(Color.BLACK);
        g.drawLine(startPosX + 90, startPosY + 10,startPosX + 110, startPosY + 10 );
        //Шасси самолёта
        g.setColor(Color.BLACK);
        g.drawLine(startPosX + 80, startPosY + 20,startPosX + 80, startPosY + 25 );
        g.drawOval(startPosX + 79 ,startPosY + 26, 3, 3);
        g.setColor(Color.BLACK);
        g.drawLine(startPosX + 40, startPosY + 20,startPosX + 40, startPosY + 25 );
        g.drawOval(startPosX + 36 ,startPosY + 26, 3, 3);
        g.drawOval(startPosX + 41 ,startPosY + 26, 3, 3);
        //Двигатели самолёта
        g.setColor(Color.YELLOW);
        g.fillOval(startPosX + 5 ,startPosY - 5, 18, 4);
        g.setColor(Color.BLACK);
        g.drawOval(startPosX + 5 ,startPosY - 5, 18, 4);
        //Крылья самолёта
        g.setColor(Color.YELLOW);
        g.fillOval(startPosX + 50 ,startPosY + 8, 18, 4);
        g.setColor(Color.BLACK);
        g.drawOval(startPosX + 50 ,startPosY + 8, 18, 4);
    }

    @Override
    public void moveTransport(Direction direction) {
        float step = maxSpeed * 100 / weight;
        switch (direction) {
            case Right:
                if (startPosX + step < pictureWidth - PlaneWidth * changeWidth) {
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
                if (startPosY + step < pictureHeight - PlaneHeight * changeHeight) {
                    startPosY += step;
                }
                break;
        }
    }

    @Override
    public String toString() {
        return maxSpeed + separator + weight + separator + mainColor.getRGB();
    }
}
