package logic;

import objects.FabricLabWorks;
import objects.LabWork;

import java.util.ArrayList;
import java.util.HashMap;

public class Editor {
    HashMap<String, LabWork> collection;

    public Editor() {
        FabricLabWorks fabricLabWorks = new FabricLabWorks();
        collection = fabricLabWorks.getTestingMaterial();
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

    public void removeElementByKey(String key) {
        collection.remove(key);
    }

    public void clear() {
        collection.clear();
    }

    public void removeAllLowerThanByKey(String key) {
        Long minimalPoint = collection.get(key).getMinimalPoint();
        ArrayList<String> toDeleteKeys = new ArrayList<>();
        for (String key2: collection.keySet()) {
            if (collection.get(key2).getMinimalPoint() < minimalPoint) toDeleteKeys.add(key2);
        }
        // without ArrayList I got some error message: ConcurrentModificationException
        for (String key3: toDeleteKeys) collection.remove(key3);
    }
}
