package com.company;
import javax.swing.*;
import java.awt.*;

public class DrawPicture extends JPanel{
    private Hydroplane hydroplane;

    public void paintComponent(Graphics g) {
        if (hydroplane != null) {
            hydroplane.drawPicture(g);
        }
    }

    public void setPlane(Hydroplane pl) {
        this.hydroplane = pl;
    }

    public Hydroplane getPlane() {
        return hydroplane;
    }
}
