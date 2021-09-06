package com.example.cassandrademo.utils;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pair<First, Second> {
    First first;
    Second second;

    public static<First, Second> Pair<First, Second> of(First first, Second second) {
        return new Pair<>(first, second);
    }
}
