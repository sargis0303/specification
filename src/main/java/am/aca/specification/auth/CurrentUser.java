package am.aca.specification.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class CurrentUser extends User {

    private Long id;
    private String email;
    private String password;
    private boolean isActive;
    private String role;

    public CurrentUser(Long id, String email, String password, boolean isActive, String role) {
        super(email, password, buildUserAuthority(role));

        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }

    private static Set<GrantedAuthority> buildUserAuthority(String systemRole) {
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        grantedAuthoritySet.add(new SimpleGrantedAuthority(systemRole));

        return grantedAuthoritySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CurrentUser that = (CurrentUser) o;
        return id.equals(that.id) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, email);
    }
}
