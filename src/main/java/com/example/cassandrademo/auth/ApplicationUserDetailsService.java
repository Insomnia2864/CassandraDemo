package com.example.cassandrademo.auth;

import com.example.cassandrademo.repository.UsersRepository;
import com.example.cassandrademo.tables.User;
import com.example.cassandrademo.utils.GroupResolver;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationUserDetailsService implements UserDetailsService {

    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(String.format("No user %s", username)));
        return mapToUserDetails(user);
    }

    public ApplicationUserDetails mapToUserDetails(User user) {
        try {
            return ApplicationUserDetails.builder()
                    .isAccountNonExpired(user.isAccountNonExpired())
                    .isAccountNonLocked(user.isAccountNonLocked())
                    .isCredentialsNonExpired(user.isCredentialsNonExpired())
                    .isEnabled(user.isEnabled())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(GroupResolver.getGroup(user.getGroup()).get().getGrantedAuthorities())
                    .build();
        }
        catch (NullPointerException exception) {
            log.warn("an't define group for user {}", user.getUsername());
            throw new UsernameNotFoundException(String.format("Can't define group for user %s", user.getUsername()));
        }
    }
}
