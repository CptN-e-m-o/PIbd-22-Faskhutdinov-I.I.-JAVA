package com.company;

import java.awt.*;

public class FloatTriangle implements Adding {

    private FloatCount floatcount;

    public FloatTriangle(int number) {
        setDigit(number);
    }

    @Override
    public void setDigit(int number) {
        this.floatcount = FloatCount.getCount(number);
    }

    @Override
    public void draw(Graphics g, int startPosX, int startPosY, int planeWidth, int planeHeight, Color Dopcolor) {
        if (floatcount == FloatCount.two || floatcount == FloatCount.four || floatcount == FloatCount.six) {
            g.setColor(Color.BLACK);
            g.drawLine(startPosX + 90, startPosY + 20, startPosX + 90, startPosY + 30);
            g.drawLine(startPosX + 95, startPosY + 18, startPosX + 95, startPosY + 28);

            int[] hydrF_points_x = new int[3];
            int[] hydrF_points_y = new int[3];

            hydrF_points_x[0] = startPosX + 90;
            hydrF_points_x[1] = startPosX + 100;
            hydrF_points_x[2] = startPosX + 95;
            hydrF_points_y[0] = startPosY + 28;
            hydrF_points_y[1] = startPosY + 28;
            hydrF_points_y[2] = startPosY + 33;
            g.setColor(Dopcolor);
            g.fillPolygon(hydrF_points_x,hydrF_points_y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(hydrF_points_x, hydrF_points_y, 3);

            hydrF_points_x[0] = startPosX + 85;
            hydrF_points_x[1] = startPosX + 95;
            hydrF_points_x[2] = startPosX + 90;
            hydrF_points_y[0] = startPosY + 30;
            hydrF_points_y[1] = startPosY + 30;
            hydrF_points_y[2] = startPosY + 35;
            g.setColor(Dopcolor);
            g.fillPolygon(hydrF_points_x,hydrF_points_y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(hydrF_points_x, hydrF_points_y, 3);

        }
        if (floatcount == FloatCount.four || floatcount == FloatCount.six) {
            g.setColor(Color.BLACK);
            g.drawLine(startPosX + 50, startPosY + 20, startPosX + 50, startPosY + 30);
            g.drawLine(startPosX + 70, startPosY + 20, startPosX + 70, startPosY + 30);
            int[] hydrF_points_x = new int[3];
            int[] hydrF_points_y = new int[3];

            hydrF_points_x[0] = startPosX + 45;
            hydrF_points_x[1] = startPosX + 55;
            hydrF_points_x[2] = startPosX + 50;
            hydrF_points_y[0] = startPosY + 30;
            hydrF_points_y[1] = startPosY + 30;
            hydrF_points_y[2] = startPosY + 35;
            g.setColor(Dopcolor);
            g.fillPolygon(hydrF_points_x,hydrF_points_y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(hydrF_points_x, hydrF_points_y, 3);

            hydrF_points_x[0] = startPosX + 65;
            hydrF_points_x[1] = startPosX + 75;
            hydrF_points_x[2] = startPosX + 70;
            hydrF_points_y[0] = startPosY + 30;
            hydrF_points_y[1] = startPosY + 30;
            hydrF_points_y[2] = startPosY + 35;
            g.setColor(Dopcolor);
            g.fillPolygon(hydrF_points_x,hydrF_points_y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(hydrF_points_x, hydrF_points_y, 3);
        }
        if (floatcount == FloatCount.six) {
            g.setColor(Color.BLACK);
            g.drawLine(startPosX + 25, startPosY + 20, startPosX + 25, startPosY + 30);
            g.drawLine(startPosX + 15, startPosY + 20, startPosX + 15, startPosY + 30);
            int[] hydrF_points_x = new int[3];
            int[] hydrF_points_y = new int[3];

            hydrF_points_x[0] = startPosX + 20;
            hydrF_points_x[1] = startPosX + 30;
            hydrF_points_x[2] = startPosX + 25;
            hydrF_points_y[0] = startPosY + 30;
            hydrF_points_y[1] = startPosY + 30;
            hydrF_points_y[2] = startPosY + 35;
            g.setColor(Dopcolor);
            g.fillPolygon(hydrF_points_x,hydrF_points_y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(hydrF_points_x, hydrF_points_y, 3);

            hydrF_points_x[0] = startPosX + 10;
            hydrF_points_x[1] = startPosX + 20;
            hydrF_points_x[2] = startPosX + 15;
            hydrF_points_y[0] = startPosY + 30;
            hydrF_points_y[1] = startPosY + 30;
            hydrF_points_y[2] = startPosY + 35;
            g.setColor(Dopcolor);
            g.fillPolygon(hydrF_points_x,hydrF_points_y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(hydrF_points_x, hydrF_points_y, 3);
        }
    }
}