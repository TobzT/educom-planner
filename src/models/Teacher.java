package models;

public class Teacher extends Person {

    protected final boolean isTeacher = true;
    public Teacher(String name) {
        super(name);
    }
}
