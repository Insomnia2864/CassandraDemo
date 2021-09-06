package com.example.cassandrademo.services;

import com.example.cassandrademo.tables.CommonImage;
import com.example.cassandrademo.tables.Image;
import com.example.cassandrademo.tables.SecretImage;
import com.example.cassandrademo.utils.Pair;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImagesMapper {

    Base64.Encoder encoder = Base64.getEncoder();
    Base64.Decoder decoder = Base64.getDecoder();

    public SecretImage mapToImageSecret(byte[] content, String name) {
        return SecretImage.builder()
                .imageBase64(encoder.encodeToString(content))
                .name(name)
                .build();
    }

    public CommonImage mapToCommonImage(byte[] content, String name) {
        return CommonImage.builder()
                .imageBase64(encoder.encodeToString(content))
                .name(name)
                .build();
    }

    public Pair<String, byte[]> mapToRawImage(Image image) {
        return Pair.of(image.getName(), decoder.decode(image.getData()));
    }
}
