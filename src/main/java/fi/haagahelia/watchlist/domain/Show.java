package fi.haagahelia.watchlist.domain;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category; // "Anime", "TV Show", "Movie"
    private int episodesWatched;
    private int totalEpisodes;
    private String status; // "Watching", "Completed", etc.
    private LocalDateTime dateAdded;

    @PrePersist
    protected void onCreate() {
        dateAdded = LocalDateTime.now();
    }

    @OneToOne(mappedBy = "show", cascade = CascadeType.ALL)
    private Rating rating;

    public Show() {

    }

    public Show(String title, String category, int totalEpisodes, LocalDateTime dateAdded) {
        this.title = title;
        this.category = category;
        this.totalEpisodes = totalEpisodes;
        this.episodesWatched = 0;
        this.status = "Plan to Watch";
        this.dateAdded = dateAdded;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getEpisodesWatched() {
        return episodesWatched;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public String getStatus() {
        return status;
    }

    public Rating getRating() {
        return rating;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    // Setters
    public void setEpisodesWatched(int episodesWatched) {
        this.episodesWatched = episodesWatched;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }
}
