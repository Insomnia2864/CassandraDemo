package com.example.cassandrademo.tables;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @PrimaryKey
    @Column(value = "username")
    String username;
    @Column(value = "password")
    String password;
    @Column(value = "group")
    String group;
    @Column(value = "is_account_non_expired")
    boolean isAccountNonExpired;
    @Column(value = "is_account_non_locked")
    boolean isAccountNonLocked;
    @Column(value = "is_credentials_non_expired")
    boolean isCredentialsNonExpired;
    @Column(value = "is_enabled")
    boolean isEnabled;
}
