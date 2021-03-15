package logic;

import input_exceptions.MoreThanException;
import objects.Coordinates;
import objects.Difficulty;

public class InputData {
    private String commandArg; //Поле может быть null Строка не может быть пустой
    private String labName; //Поле не может быть null Строка не может быть пустой
    private int coordinateX; //Максимальное значение поля: 71 Поле может быть null
    private int coordinateY; //Максимальное значение поля: 556, Поле не может быть null
    private Long minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private String disciplineName; //Поле не может быть null Строка не может быть пустой
    private Long selfStudyHours; //Поле может быть null

    public String getCommandArg() {
        return commandArg;
    }

    public void setCommandArg(String commandArg) {
        this.commandArg = commandArg;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) throws MoreThanException {
        if (coordinateX > 71) throw new MoreThanException(71);
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) throws MoreThanException {
        if (coordinateX > 556) throw new MoreThanException(556);
        this.coordinateY = coordinateY;
    }

    public Long getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(Long minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = Difficulty.valueOf(difficulty);
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public Long getSelfStudyHours() {
        return selfStudyHours;
    }

    public void setSelfStudyHours(Long selfStudyHours) {
        this.selfStudyHours = selfStudyHours;
    }
}
