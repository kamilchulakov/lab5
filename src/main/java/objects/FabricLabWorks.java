package objects;

import java.util.HashMap;

public class FabricLabWorks {
    public HashMap<String, LabWork> getTestingMaterial() {
        HashMap<String, LabWork> hashMap = new HashMap<>();
        hashMap.put("1", new LabWork("proga", new Coordinates(1.0f, 2), 2L,
                Difficulty.EASY, new Discipline("java", 10L)));
        hashMap.put("2", new LabWork("proga", new Coordinates(1.0f, 2), 10L,
                Difficulty.EASY, new Discipline("java", 10L)));
        return hashMap;
    }
}
