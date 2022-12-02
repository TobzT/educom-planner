package models;

import java.util.List;

public class Lesson {

    private Teacher teacher = null;

    private Classroom classroom = null;

    private List<Student> students = null;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {this.students.add(student); }

    public void removeStudent(int index) {this.students.remove(index); }
}
