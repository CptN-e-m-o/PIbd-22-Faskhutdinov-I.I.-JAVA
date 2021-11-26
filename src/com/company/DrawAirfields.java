package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawAirfields extends JPanel {
    private final AirfieldCollection airfieldCollection;
    private String selectedItem = null;

    public DrawAirfields(AirfieldCollection campCollection) {
        this.airfieldCollection = campCollection;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (selectedItem != null) {
            if (airfieldCollection != null) {
                airfieldCollection.get(selectedItem).draw(g);
            }
        }
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
}