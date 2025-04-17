package fi.haagahelia.watchlist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fi.haagahelia.watchlist.domain.Show;
import fi.haagahelia.watchlist.domain.ShowRepository;

public class ShowDateController {

    @Autowired
    private ShowRepository showRepository;

    @GetMapping("/watchlist/view/{userId}")
    public String viewWatchlist(@PathVariable Long userId, Model model) {
        List<Show> watchlist = showRepository.findByUserIdOrderByDateAddedDesc(userId);
        model.addAttribute("watchlist", watchlist);
        return "watchlist";
    }

}
