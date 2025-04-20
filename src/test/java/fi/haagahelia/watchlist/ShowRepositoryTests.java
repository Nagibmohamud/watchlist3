package fi.haagahelia.watchlist;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.watchlist.domain.Show;
import fi.haagahelia.watchlist.domain.ShowRepository;

@SpringBootTest(classes = WatchlistApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShowRepositoryTests {

    @Autowired
    private ShowRepository repository;

    @Test
    public void FindAllByOrderByDateAddedDesc() {
        Show show1 = new Show("Naruto", "Anime", 220, LocalDateTime.now().minusDays(1));
        Show show2 = new Show("Breaking Bad", "TV Show", 62, LocalDateTime.now());

        repository.save(show1);
        repository.save(show2);

        List<Show> shows = repository.findAllByOrderByDateAddedDesc();
        assertEquals(2, shows.size());
        assertEquals("Breaking Bad", shows.get(0).getTitle());
        assertEquals("Naruto", shows.get(1).getTitle());

    }

}
