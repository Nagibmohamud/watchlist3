package fi.haagahelia.watchlist.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.watchlist.domain.Show;
import fi.haagahelia.watchlist.domain.ShowRepository;

@RestController
@RequestMapping("/api/shows")
public class ShowRestController {

    @Autowired
    private ShowRepository showRepository;

    // Get all shows
    @GetMapping
    public List<Show> getAllShows() {
        return (List<Show>) showRepository.findAll();
    }

    // Get single show by ID
    @GetMapping("/{id}")
    public Optional<Show> getShowById(@PathVariable Long id) {
        return showRepository.findById(id);
    }

    // Add new show
    @PostMapping
    public Show addShow(@RequestBody Show show) {
        return showRepository.save(show);
    }

    // Update existing show
    @PutMapping("/{id}")
    public Show updateShow(@RequestBody Show updatedShow, @PathVariable Long id) {
        updatedShow.setId(id);
        return showRepository.save(updatedShow);
    }

    // Delete show
    @DeleteMapping("/{id}")
    public void deleteShow(@PathVariable Long id) {
        showRepository.deleteById(id);
    }
}
