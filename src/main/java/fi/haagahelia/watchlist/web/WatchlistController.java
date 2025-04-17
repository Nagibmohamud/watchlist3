package fi.haagahelia.watchlist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.watchlist.domain.Rating;
import fi.haagahelia.watchlist.domain.RatingRepository;
import fi.haagahelia.watchlist.domain.Show;
import fi.haagahelia.watchlist.domain.ShowRepository;

@Controller

public class WatchlistController {

    @Autowired
    ShowRepository showrepository;

    @Autowired
    RatingRepository ratingRepository;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/watchlist")
    public String showWatchlist(Model model) {
        model.addAttribute("shows", showrepository.findAll()); //Homepage, gets all shows from the repository
        return "watchlist";
    }

    @GetMapping("/addShow")
    public String showAddForm(Model model) {
        model.addAttribute("show", new Show()); //Gets the show object from the repository and adds it to the model
        return "addShow";
    }

    @PostMapping("/addShow")
    public String addShow(@ModelAttribute Show show) {
        showrepository.save(show);
        return "redirect:/watchlist";
    }

    @GetMapping("/deleteShow/{id}")
    public String deleteShow(@PathVariable Long id) {
        showrepository.deleteById(id);
        return "redirect:/watchlist";
    }

    @GetMapping("/editShow/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("show", showrepository.findById(id).orElse(null));
        return "editShow";
    }

    @PostMapping("/editShow")
    public String updateShow(Show show) {
        showrepository.save(show);
        return "redirect:/watchlist";
    }

    @GetMapping("/rateShow/{id}")
    public String showRateForm(@PathVariable Long id, Model model) {
        Show show = showrepository.findById(id).orElse(null);
        model.addAttribute("show", show);
        return "rateShow";
    }

    @PostMapping("/rateShow/{id}")
    public String submitRating(@PathVariable("id") Long showId,
            @RequestParam int score,
            @RequestParam String comment) {

        Show show = showrepository.findById(showId).orElseThrow();

        Rating rating = show.getRating(); // Check if there's already a rating

        if (rating == null) {
            rating = new Rating(score, comment, show);
        } else {
            rating.setScore(score);
            rating.setComment(comment);
        }

        show.setRating(rating);
        rating.setShow(show); // Ensure both ends are linked

        ratingRepository.save(rating);
        showrepository.save(show); // Optional if cascading

        return "redirect:/watchlist";
    }

}
