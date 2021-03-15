package logic;

import objects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Editor {
    HashMap<String, LabWork> collection;

    public Editor() {
        FabricLabWorks fabricLabWorks = new FabricLabWorks();
        collection = fabricLabWorks.getTestingMaterial();
    }
    public Editor(String filename) {
        this();
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

    public void removeAllLowerByLabwork(LabWork labWork) {
        ArrayList<String> toDeleteKeys = new ArrayList<>();
        for (String key: collection.keySet()) {
            if (collection.get(key).compareTo(labWork) < 0) toDeleteKeys.add(key);
        }
        for (String key3: toDeleteKeys) collection.remove(key3);
    }

    public LabWork getElementFromString(String labwork) throws NoDifficultyFoundException {
        String[] vars = labwork.split(" ");
        return new LabWork(vars[0], new Coordinates(Integer.parseInt(vars[1]),
                Integer.parseInt(vars[2])), Long.valueOf(vars[3]),
                Difficulty.get(vars[4]), new Discipline(vars[5], Long.valueOf(vars[6])));
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
            } // else System.out.println(collection.get(key).getDiscipline());
        }
        return "No matches.";
    }

    public void insert(String key, LabWork labwork) {
        collection.put(key, labwork);
    }
}
