package es.sgie.back.security;

import es.sgie.back.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

/**
 * Mapper to set roles in JWT token
 */
public class UserDetailsMapper {
    public static UserDetails build(User user) {
        return new org.springframework.security.core.userdetails.User(user.getPic(), user.getPassword(),
                getAuthorities(user));
    }

    /**
     * Add granted roles to user
     */
    private static Set<? extends GrantedAuthority> getAuthorities(User u) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole()));
        return authorities;
    }
}
