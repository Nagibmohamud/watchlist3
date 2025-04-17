package fi.haagahelia.watchlist.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    /*  Ylin arvosana (score) koko tietokannasta
    @Query("SELECT MAX(r.score) FROM Rating r")
    Integer findMaxScore();

    // Alin arvosana (score) koko tietokannasta
    @Query("SELECT MIN(r.score) FROM Rating r")
    Integer findMinScore();*/
}
