package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AirfieldCollection {

    private final Map<String, Airfield<ITransport, IAdding>> airfieldStages;

    private final int pictureWidth;

    private final int pictureHeight;

    private final String separator = ":";

    public AirfieldCollection(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        airfieldStages = new HashMap<>();
    }

    public Set<String> keys() {
        return airfieldStages.keySet();
    }

    public void addAirfield(String name) {
        if (airfieldStages.containsKey(name)) {
            return;
        }
        airfieldStages.put(name, new Airfield<>(pictureWidth, pictureHeight));
    }

    public void deleteAirfield(String name) {
        airfieldStages.remove(name);
    }

    public Airfield<ITransport, IAdding> get(String name) {
        if (airfieldStages.containsKey(name)) {
            return airfieldStages.get(name);
        }
        return null;
    }

    public boolean saveData(String filename) {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            fileWriter.write("AirfieldCollection\n");
            for (Map.Entry<String, Airfield<ITransport, IAdding>> level : airfieldStages.entrySet()) {
                fileWriter.write("Airfield" + separator + level.getKey() + '\n');
                ITransport transport;
                for (int i = 0; (transport = level.getValue().get(i)) != null; i++) {
                    if (transport.getClass().getSimpleName().equals("Plane")) {
                        fileWriter.write("Plane" + separator);
                    } else if (transport.getClass().getSimpleName().equals("Hydroplane")) {
                        fileWriter.write("Hydroplane" + separator);
                    }
                    fileWriter.write(transport.toString() + '\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadData(String filename) {
        if (!(new File(filename).exists())) {
            return false;
        }

        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            if (scanner.nextLine().contains("AirfieldCollection")) {
                airfieldStages.clear();
            } else {
                return false;
            }

            ITransport transport = null;
            String key = "";
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("Airfield")) {
                    key = line.split(separator)[1];
                    airfieldStages.put(key, new Airfield<>(pictureWidth, pictureHeight));
                } else if (line.contains(separator)) {
                    if (line.contains("Plane")) {
                        transport = new Plane(line.split(separator)[1]);
                    } else if (line.contains("Hydroplane")) {
                        transport = new Hydroplane(line.split(separator)[1]);
                    }
                    if (!(airfieldStages.get(key).add(transport))) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean saveCamp(String filename, String key) {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        if (!airfieldStages.containsKey(key)) {
            return false;
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            if (airfieldStages.containsKey(key))
                fileWriter.write("Airfield" + separator + key + '\n');
            ITransport transport;
            for (int i = 0; (transport = airfieldStages.get(key).get(i)) != null; i++) {
                if (transport.getClass().getSimpleName().equals("Plane")) {
                    fileWriter.write("Plane" + separator);
                } else if (transport.getClass().getSimpleName().equals("Hydroplane")) {
                    fileWriter.write("Hydroplane" + separator);
                }
                fileWriter.write(transport.toString() + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadCamp(String filename) {
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            String key;
            String line;
            line = scanner.nextLine();
            if (line.contains("Airfield:")) {
                key = line.split(separator)[1];
                if (airfieldStages.containsKey(key)) {
                    airfieldStages.get(key).clear();
                } else {
                    airfieldStages.put(key, new Airfield<>(pictureWidth, pictureHeight));
                }
            } else {
                return false;
            }
            ITransport transport = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(separator)) {
                    if (line.contains("Plane")) {
                        transport = new Plane(line.split(separator)[1]);
                    } else if (line.contains("Hydroplane")) {
                        transport = new Hydroplane(line.split(separator)[1]);
                    }
                    if (!(airfieldStages.get(key).add(transport))) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ITransport get(String name, int index) {
        if (airfieldStages.containsKey(name)) {
            return airfieldStages.get(name).get(index);
        }
        return null;
    }
}