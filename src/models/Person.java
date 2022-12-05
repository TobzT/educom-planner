package models;

public class Person extends Object {

    protected String name;
    protected String subject1;
    protected String subject2;

    protected boolean isTeacher;

    public Person(String name) {
        this.name = name;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public String toString() {
        return name;
    }

}
