package fr.uge.jee.hibernate.students;

import jakarta.persistence.*;

@Entity
@Table(name = "Univesity")
public class University {
    @Id
    @GeneratedValue
    @Column(name="UNIVERSITYID")
    private long id;
    @Column(name = "NAME_UNIVERSITY")
    private String nameUniversity;

    public University() {}
    public University(String name) {
        this.nameUniversity = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return nameUniversity;
    }

    public void setAddress(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }
}
