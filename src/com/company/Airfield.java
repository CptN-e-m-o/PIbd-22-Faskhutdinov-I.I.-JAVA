package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Airfield<T extends ITransport, G extends IAdding> {

    private final List<T> places;

    private final int pictureWidth;

    private final int maxCount;

    private final int pictureHeight;

    private final int placeSizeWidth = 140;

    private final int placeSizeHeight = 80;

    public Airfield(int picWidth, int picHeight) {
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        maxCount = width * height;
        places = new ArrayList<>();
    }

    public boolean add(T vehicle) {
        if (places.size() < maxCount) {
            places.add(vehicle);
            return true;
        }
        return false;
    }

    public T delete(int index) {
        if (index >= 0 && index < maxCount && places.get(index) != null) {
            T plane = places.get(index);
            places.remove(index);
            return plane;
        }
        return null;
    }


    public void draw(Graphics g) {
        int width = pictureWidth / placeSizeWidth;
        int height = pictureHeight / placeSizeHeight;
        drawMarking(g);
        for (int i = 0; i < places.size(); i++) {
            places.get(i).setPosition(i % width * placeSizeWidth + 15,
                    height + i / height * placeSizeHeight + 10,
                    pictureWidth, pictureHeight);
            places.get(i).draw(g);
        }
    }


    private void drawMarking(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < pictureWidth / placeSizeWidth; i++)
        {
            for (int j = 0; j < pictureHeight / placeSizeHeight + 1; ++j)
            {//линия разметки места
                g.drawLine(i * placeSizeWidth, j * placeSizeHeight, i *
                        placeSizeWidth + placeSizeWidth / 2, j * placeSizeHeight);
            }
            g.drawLine(i * placeSizeWidth, 0, i * placeSizeWidth,
                    (pictureHeight / placeSizeHeight) * placeSizeHeight);
        }
    }

    public T get(int index) {
        if (index > -1 && index < places.size()) {
            return places.get(index);
        }
        return null;
    }

    public void clear() {
        places.clear();
    }
}