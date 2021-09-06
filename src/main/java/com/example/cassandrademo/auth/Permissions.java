package com.example.cassandrademo.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permissions {
    READ_SPECIAL("special:read"),
    WRITE_SPECIAL("special:write"),
    READ_COMMON("common:read"),
    WRITE_COMMON("common:write");

    private final String permission;
}
