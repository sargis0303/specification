package am.aca.specification.auth;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService implements UserDetailsService {

    @Override
    public CurrentUser loadUserByUsername(String email) {
        return new CurrentUser(
                1L, "test@test.test",
                "$2a$12$yhNrCSP3FSEIfir0pcC9p.DrsPo75duUWGArKqzCW/fO0oQoVGUF2", // test
                true,
                "USER"
        );
    }

}
