package logic;

import objects.*;

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

    public String getAverageMinimalPoint() {
        long result = 0;
        if (collection.size() == 0) return String.valueOf(result);
        for (LabWork labWork: collection.values()) result += labWork.getMinimalPoint();
        return String.valueOf(result / collection.size());
    }

    public String getDescendingDifficulty() {
        ArrayList<Difficulty> difficulties = new ArrayList<>();
        for (LabWork labwork: collection.values()
             ) {
            difficulties.add(labwork.getDifficulty());
        }
        difficulties.sort(new DifficultyComparator());
        StringBuilder result = new StringBuilder();
        for (Difficulty difficulty: difficulties) result.append(difficulty.toString()).append(" ");
        return result.toString();
    }

    public String removeByDiscipline(Discipline discipline) {
        for (String key: collection.keySet()) {
            if (collection.get(key).getDiscipline().equals(discipline)) {
                collection.remove(key);
                return "Successfully removed element.";
            } else System.out.println(collection.get(key).getDiscipline());
        }
        return "No matches.";
    }
}
