package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawAirfield extends JPanel {
    private final Airfield<Plane, Adding> planeAirfield;

    public DrawAirfield(Airfield<Plane, Adding> trackedVehicleCamp) {
        this.planeAirfield = trackedVehicleCamp;
    }

    protected void paintComponent(Graphics g) {
        if (planeAirfield != null) {
            planeAirfield.draw(g);
        }
    }

    public Airfield<Plane, Adding> getPlaneAirfield() {
        return planeAirfield;
    }
}