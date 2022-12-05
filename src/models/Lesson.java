package models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Lesson {

    private Teacher teacher = null;

    private Classroom classroom = null;

    private List<Student> students = null;

    private List<String> labeltexts = new ArrayList<String>();

    private TPanel teacherPanel = null;
    private TPanel[] studentPanels = null;

    public Lesson(TPanel teacherPanel, TPanel[] studentPanels) {
        this.teacherPanel = teacherPanel;
        this.studentPanels = studentPanels;
    }

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

    public JPanel getTeacherPanel() {
        return teacherPanel;
    }

    public void setTeacherPanel(TPanel teacherPanel) {
        this.teacherPanel = teacherPanel;
    }

    public TPanel[] getStudentPanels() {
        return studentPanels;
    }

    public void setStudentPanels(TPanel[] studentPanels) {
        this.studentPanels = studentPanels;
    }

    public void addStudent(Student student) {this.students.add(student); }

    public void removeStudent(int index) {this.students.remove(index); }
}
