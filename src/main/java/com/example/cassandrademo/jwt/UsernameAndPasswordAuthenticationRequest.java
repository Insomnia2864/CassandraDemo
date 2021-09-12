package com.example.cassandrademo.jwt;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class UsernameAndPasswordAuthenticationRequest {
    String username;
    String password;
}
