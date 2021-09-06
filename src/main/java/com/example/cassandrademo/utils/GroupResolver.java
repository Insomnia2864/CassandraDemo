package com.example.cassandrademo.utils;

import com.example.cassandrademo.auth.Groups;

import java.util.Optional;

public class GroupResolver {
    public static Optional<Groups> getGroup(String group) {
        return switch (group.toLowerCase()) {
            case "admin" -> Optional.of(Groups.Admin);
            case "user" -> Optional.of(Groups.User);
            default -> Optional.empty();
        };

    }
}
