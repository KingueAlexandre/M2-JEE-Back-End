package fr.uge.jee;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import javax.persistence.GeneratedValue;

public class Pokemon {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int score;
    @Version
    private Long version;

    public Pokemon(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
