package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AirfieldCollection {

    private final Map<String, Airfield<Transport, Adding>> AirfieldStages;

    private final int pictureWidth;

    private final int pictureHeight;

    public AirfieldCollection(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        AirfieldStages = new HashMap<>();
    }

    public Set<String> keys() {
        return AirfieldStages.keySet();
    }

    public void addAirfield(String name) {
        if (AirfieldStages.containsKey(name)) {
            return;
        }
        AirfieldStages.put(name, new Airfield<>(pictureWidth, pictureHeight));
    }

    public void deleteAirfield(String name) {
        AirfieldStages.remove(name);
    }

    public Airfield<Transport, Adding> get(String name) {
        if (AirfieldStages.containsKey(name)) {
            return AirfieldStages.get(name);
        }
        return null;
    }

    public Transport get(String name, int index) {
        if (AirfieldStages.containsKey(name)) {
            return AirfieldStages.get(name).get(index);
        }
        return null;
    }
}