package logic;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import objects.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Editor {
    HashMap<String, LabWork> collection;

    public Editor() {
        FabricLabWorks fabricLabWorks = new FabricLabWorks();
        collection = fabricLabWorks.getTestingMaterial();
    }
    public Editor(String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            collection = mapper.readValue(Paths.get(filename).toFile(), HashMap.class);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

    public void removeAllLowerByLabwork(LabWork labWork) {
        ArrayList<String> toDeleteKeys = new ArrayList<>();
        for (String key: collection.keySet()) {
            if (collection.get(key).compareTo(labWork) < 0) toDeleteKeys.add(key);
        }
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
            }
        }
        return "No matches.";
    }

    public void update(int id, LabWork labWork) {
        boolean notFound = true;
        for (String key: collection.keySet()) {
            LabWork labWork1 = collection.get(key);
            if (labWork1.getId() == id) {
                labWork1.copyFromLabwork(labWork);
                notFound = false;
            }
        }
        if (notFound) throw new NoSuchElementException();

    }

    public void insert(String key, LabWork labwork) {
        collection.put(key, labwork);
    }

    public void save(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new FileOutputStream(Paths.get(filename).toFile()), collection);
    }

    public void removeIfLower(String key, LabWork labWork) {
        if (collection.get(key).compareTo(labWork) < 0) collection.remove(key);
    }
}
