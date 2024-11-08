package ClassScheduling;

import java.util.Objects;

public class Course {
    private String name;
    private String professor;
    private int students;
    private int duration;

    public Course(String name, String professor, int students, int duration){
        this.name = name;
        this.professor = professor;
        this.students = students;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public int getStudents() {
        return students;
    }

    public String getProfessor() {
        return professor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return getStudents() == course.getStudents() && getDuration() == course.getDuration() && Objects.equals(getName(), course.getName()) && Objects.equals(getProfessor(), course.getProfessor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getProfessor(), getStudents(), getDuration());
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", professor='" + professor + '\'' +
                ", students=" + students +
                ", duration=" + duration +
                '}';
    }
}
