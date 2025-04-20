package fi.haagahelia.watchlist;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.watchlist.domain.Account;
import fi.haagahelia.watchlist.domain.AccountRepository;

@SpringBootTest(classes = WatchlistApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository repository;

    @Test
    public void findByNameShouldReturnUser() {
        Account user = repository.findByUsername("admin");

        assertThat(user).isNotNull();
        assertThat(user.getRole()).isEqualTo("ADMIN");
    }

    @Test
    public void createNewUser() {
        Account user = new Account("testuser", "password", "USER");
        repository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void deleteNewUser() {
        Account user = repository.findByUsername("user");
        repository.delete(user);
        Account newUser = repository.findByUsername("user");
        assertThat(newUser).isNull();
    }

}
