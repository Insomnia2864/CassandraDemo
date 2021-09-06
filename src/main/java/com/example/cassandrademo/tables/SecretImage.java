package com.example.cassandrademo.tables;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "secret_images")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecretImage implements Image {

    @PrimaryKey
    @Column(value = "name")
    String name;
    @Column(value = "image_base64")
    String imageBase64;

    @Override
    public String getData() {
        return imageBase64;
    }

    @Override
    public String getName() {
        return name;
    }
}
