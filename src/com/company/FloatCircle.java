package com.company;

import java.awt.*;

public class FloatCircle implements Adding {

    private FloatCount floatcount;

    public FloatCircle(int number) {
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
            g.setColor(Dopcolor);
            g.fillOval(startPosX + 85, startPosY + 30, 12,8);
            g.fillOval(startPosX + 90, startPosY + 28, 12,8);
            g.setColor(Color.BLACK);
            g.drawOval(startPosX + 90, startPosY + 28, 12,8);
            g.drawOval(startPosX + 85, startPosY + 30, 12,8);
        }
        if (floatcount == FloatCount.four || floatcount == FloatCount.six) {
            g.setColor(Color.BLACK);
            g.drawLine(startPosX + 50, startPosY + 20, startPosX + 50, startPosY + 30);
            g.drawLine(startPosX + 70, startPosY + 20, startPosX + 70, startPosY + 30);
            g.setColor(Dopcolor);
            g.fillOval(startPosX + 45, startPosY + 30, 12,8);
            g.fillOval(startPosX + 65, startPosY + 30, 12,8);
            g.setColor(Color.BLACK);
            g.drawOval(startPosX + 45, startPosY + 30, 12,8);
            g.drawOval(startPosX + 65, startPosY + 30, 12,8);
        }
        if (floatcount == FloatCount.six) {
            g.setColor(Color.BLACK);
            g.drawLine(startPosX + 25, startPosY + 20, startPosX + 25, startPosY + 30);
            g.drawLine(startPosX + 15, startPosY + 20, startPosX + 15, startPosY + 30);
            g.setColor(Dopcolor);
            g.fillOval(startPosX + 20, startPosY + 30, 12,8);
            g.fillOval(startPosX + 10, startPosY + 30, 12,8);
            g.setColor(Color.BLACK);
            g.drawOval(startPosX + 20, startPosY + 30, 12,8);
            g.drawOval(startPosX + 10, startPosY + 30, 12,8);
        }
    }
}