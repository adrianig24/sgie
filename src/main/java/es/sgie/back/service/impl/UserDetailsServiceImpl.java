package es.sgie.back.service.impl;

import es.sgie.back.domain.User;
import es.sgie.back.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Class needed for security authentication and authorization
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String pic) throws UsernameNotFoundException {
        Optional<User> user = this.usersRepository.findByPic(pic);
        if (user.isPresent()) {
            String role = user.get().getRole();

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));

            return new org.springframework.security.core.userdetails.User(user.get().getPic(), user.get().getPassword(),
                    grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
