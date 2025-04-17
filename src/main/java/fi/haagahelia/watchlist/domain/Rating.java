package fi.haagahelia.watchlist.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int score;
    private String comment;

    @OneToOne
    @JoinColumn(name = "show_id")
    private Show show;

    public Rating() {
    }

    public Rating(int score, String comment, Show show) {
        this.score = score;
        this.comment = comment;
        this.show = show;

    }

    // Getters
    public Long getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public Show getShow() {
        return show;
    }

    public String getComment() {
        return comment;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setShow(Show show) {
        this.show = show;

    }
}
