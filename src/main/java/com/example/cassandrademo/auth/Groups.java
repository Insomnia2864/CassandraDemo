package com.example.cassandrademo.auth;

import com.datastax.oss.driver.shaded.guava.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.cassandrademo.auth.Permissions.*;

@Getter
@RequiredArgsConstructor
public enum Groups {
    Admin(Sets.newHashSet(READ_SPECIAL, WRITE_SPECIAL, READ_COMMON, WRITE_COMMON)),
    User(Sets.newHashSet(READ_COMMON));

    private final Set<Permissions> permissions;

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> authorities = getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
