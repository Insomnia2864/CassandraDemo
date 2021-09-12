package com.example.cassandrademo.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

// FIXME: spring boot doesn't inject properties, wtf?

@ConfigurationProperties(prefix = "application.jwt")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class JwtApplicationProperties {
    long expirationTimeInWeeks = 4L;
    String secretKey = "something-secure.something-secure.something-secure.something-secure.something-secure.something-secure.something-secure.something-secure.something-secure";
}
