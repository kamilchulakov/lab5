package logic;

import objects.LabWork;

import java.util.HashMap;

public class Editor {
    HashMap<String, LabWork> collection;

    public Editor() {
        collection = new HashMap<>();
        collection.put("1", null);
    }

    public HashMap<String, LabWork> getCollection() {
        return collection;
    }

    public void setCollection(HashMap<String, LabWork> collection) {
        this.collection = collection;
    }

    public String getStringCollection() {
        return collection.toString();
    }
}
