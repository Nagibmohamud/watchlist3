package fi.haagahelia.watchlist.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ShowRepository extends CrudRepository<Show, Long> {

    List<Show> findByUserIdOrderByDateAddedDesc(Long userId);

}
