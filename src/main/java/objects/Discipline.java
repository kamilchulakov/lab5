package objects;

public class Discipline {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long selfStudyHours; //Поле может быть null
    public Discipline(String name2, Long number) {
        name = name2;
        selfStudyHours = number;
    }
}
