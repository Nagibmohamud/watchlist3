package fi.haagahelia.watchlist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.watchlist.domain.Account;
import fi.haagahelia.watchlist.domain.AccountRepository;
import fi.haagahelia.watchlist.domain.Rating;
import fi.haagahelia.watchlist.domain.RatingRepository;
import fi.haagahelia.watchlist.domain.Show;
import fi.haagahelia.watchlist.domain.ShowRepository;

@SpringBootApplication
public class WatchlistApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatchlistApplication.class, args);
    }

    @Bean
    public CommandLineRunner testdata(ShowRepository showRepository, RatingRepository ratingRepository,
            AccountRepository userRepository) {
        return (args) -> {
            // Test data Shows
            Show show1 = new Show("Naruto", "Anime", 220);
            show1.setEpisodesWatched(100);
            show1.setStatus("Watching");

            Show show2 = new Show("Breaking Bad", "TV Show", 62);
            show2.setEpisodesWatched(62);
            show2.setStatus("Completed");

            Show show3 = new Show("The Avengers", "Movie", 1);
            show3.setStatus("Completed");

            // Saves the test data into the repository
            showRepository.save(show1);
            showRepository.save(show2);
            showRepository.save(show3);

            Rating rating1 = new Rating(9, "Amazing show!", show1);
            Rating rating2 = new Rating(8, "Really good, but could have been better.", show2);
            Rating rating3 = new Rating(10, "One of the best movies ever!", show3);

            ratingRepository.save(rating1);
            ratingRepository.save(rating2);
            ratingRepository.save(rating3);

            show1.setRating(rating1);
            show2.setRating(rating2);
            show3.setRating(rating3);

            // Saves shows and ratings to the database
            showRepository.save(show1);
            showRepository.save(show2);
            showRepository.save(show3);

            Account user1 = new Account("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
            Account user2 = new Account("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ROLE_ADMIN");

            // Saves users to the database
            userRepository.save(user1);
            userRepository.save(user2);
        };
    }
}
