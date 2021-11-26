package com.company;
import java.awt.*;

public class Float {

    private static FloatCount count;

    public void setNumber(int number) {
        count = FloatCount.getcount(number);
    }

    public static void DrawFloat(Graphics g, int startPosX, int startPosY, int excavatorWidth, int excavatorHeight, Color mainColor, Color dopColor) {
        if (count == FloatCount.two || count == FloatCount.four || count == FloatCount.six) {
            g.setColor(mainColor);
            g.drawLine(startPosX + 90, startPosY + 20, startPosX + 90, startPosY + 30);
            g.drawLine(startPosX + 95, startPosY + 18, startPosX + 95, startPosY + 28);
            g.setColor(dopColor);
            g.fillOval(startPosX + 85, startPosY + 30, 12,8);
            g.fillOval(startPosX + 90, startPosY + 28, 12,8);
            g.setColor(mainColor);
            g.drawOval(startPosX + 90, startPosY + 28, 12,8);
            g.drawOval(startPosX + 85, startPosY + 30, 12,8);
        }
        if (count == FloatCount.four || count == FloatCount.six) {
            g.setColor(mainColor);
            g.drawLine(startPosX + 50, startPosY + 20, startPosX + 50, startPosY + 30);
            g.drawLine(startPosX + 70, startPosY + 20, startPosX + 70, startPosY + 30);
            g.setColor(dopColor);
            g.fillOval(startPosX + 45, startPosY + 30, 12,8);
            g.fillOval(startPosX + 65, startPosY + 30, 12,8);
            g.setColor(mainColor);
            g.drawOval(startPosX + 45, startPosY + 30, 12,8);
            g.drawOval(startPosX + 65, startPosY + 30, 12,8);
        }
        if (count == FloatCount.six) {
            g.setColor(mainColor);
            g.drawLine(startPosX + 25, startPosY + 20, startPosX + 25, startPosY + 30);
            g.drawLine(startPosX + 15, startPosY + 20, startPosX + 15, startPosY + 30);
            g.setColor(dopColor);
            g.fillOval(startPosX + 20, startPosY + 30, 12,8);
            g.fillOval(startPosX + 10, startPosY + 30, 12,8);
            g.setColor(mainColor);
            g.drawOval(startPosX + 20, startPosY + 30, 12,8);
            g.drawOval(startPosX + 10, startPosY + 30, 12,8);
        }
    }
}
