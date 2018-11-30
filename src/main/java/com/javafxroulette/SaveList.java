package com.javafxroulette;

import java.io.*;
import java.util.HashMap;

public class SaveList {
    HashMap<String, Integer> saveList = new HashMap<>();

    File savedHashMaps = new File("rank.list");
    public void saveMap() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedHashMaps));
            oos.writeObject(saveList);
            oos.close();
        } catch (Exception e) {
            // obsługa błędów
        }
    }

    public void loadMap() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedHashMaps));
            Object readMap = ois.readObject();
            if(readMap instanceof HashMap) {
                saveList.putAll((HashMap) readMap);
            }
            ois.close();
        } catch (Exception e) {
            // obsługa błędów
        }
    }

    public void setSaveList(HashMap<String, Integer> saveList) {
        this.saveList = saveList;
    }
}
