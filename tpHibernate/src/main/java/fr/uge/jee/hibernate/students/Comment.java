package fr.uge.jee.hibernate.students;

import jakarta.persistence.*;

@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name="COMMENTID")
    private long id;
    @Column(name = "Comment")
    private String comment;

    public Comment() {}
    public Comment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
