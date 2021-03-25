package logic;

import input_exceptions.CancelException;
import input_exceptions.LessThanZeroException;
import input_exceptions.MoreThanException;
import objects.Coordinates;
import objects.Difficulty;

import java.util.Objects;

public class InputData {
    private String commandArg; //Поле может быть null Строка не может быть пустой
    private String labName; //Поле не может быть null Строка не может быть пустой
    private float coordinateX; //Максимальное значение поля: 71 Поле может быть null
    private float coordinateY; //Максимальное значение поля: 556, Поле не может быть null
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

    public void setLabName(String labName) throws Exception {
        if (labName.isEmpty()) throw new CancelException();
        else if (labName.equals(" ") | labName.trim().equals("")) throw new Exception();
        this.labName = labName;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) throws MoreThanException, LessThanZeroException {
        if (coordinateX > 71.0) throw new MoreThanException(71);
        else if (coordinateX < 0.0) throw new LessThanZeroException();
        this.coordinateX = coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) throws MoreThanException, LessThanZeroException {
        if (coordinateY > 556.0) throw new MoreThanException(556);
        else if (coordinateY < 0.0) throw new LessThanZeroException();
        this.coordinateY = coordinateY;
    }

    public Long getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(String minimalPointString) throws LessThanZeroException {
        if (minimalPointString == null) throw new CancelException();
        long minimalPoint = Long.parseLong(minimalPointString);
        if (minimalPoint < 0) throw new LessThanZeroException();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputData inputData = (InputData) o;
        return Float.compare(inputData.coordinateX, coordinateX) == 0 &&
                Float.compare(inputData.coordinateY, coordinateY) == 0 &&
                Objects.equals(commandArg, inputData.commandArg) &&
                Objects.equals(labName, inputData.labName) &&
                Objects.equals(minimalPoint, inputData.minimalPoint) &&
                difficulty == inputData.difficulty &&
                Objects.equals(disciplineName, inputData.disciplineName) &&
                Objects.equals(selfStudyHours, inputData.selfStudyHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandArg, labName, coordinateX, coordinateY, minimalPoint, difficulty, disciplineName, selfStudyHours);
    }
}
