package fr.uge.jee.hibernate.students;

import jakarta.persistence.*;

@Entity
@Table(name = "LECTURE")
public class Lecture {
    @Id
    @GeneratedValue
    @Column(name="LECTUREID")
    private long id;
    @Column(name = "LECTURENAME")
    private String lectureName;

    public Lecture() {}
    public Lecture(String lectureName) {
        this.lectureName = lectureName;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }
}
