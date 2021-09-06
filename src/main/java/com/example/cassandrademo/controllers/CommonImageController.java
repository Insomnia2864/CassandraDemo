package com.example.cassandrademo.controllers;

import com.example.cassandrademo.repository.CommonImageRepository;
import com.example.cassandrademo.repository.SecretImagesRepository;
import com.example.cassandrademo.services.ImagesMapper;
import com.example.cassandrademo.tables.CommonImage;
import com.example.cassandrademo.tables.SecretImage;
import com.example.cassandrademo.utils.Pair;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "images/api/common")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('ROLE_Admin', 'ROLE_User')")
public class CommonImageController {

    CommonImageRepository imagesRepository;
    ImagesMapper imagesMapper;

    @PutMapping(value = "/put")
    @PreAuthorize(value = "hasAuthority('common:write')")
    public ResponseEntity<? extends String> putImage(@RequestPart(value = "image") byte[] imageRaw, @RequestParam(value = "name") String name) {
        try {
            CommonImage image = imagesMapper.mapToCommonImage(imageRaw, name);
            imagesRepository.save(image);
            return ResponseEntity.ok().build();
        }
        catch (Exception exception) {
            log.warn("Error occurred", exception);
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/get", produces = MediaType.IMAGE_JPEG_VALUE)
    @PreAuthorize(value = "hasAuthority('common:read')")
    public ResponseEntity<byte[]> getImage(@RequestParam(value = "name") String name) {
        try {
            CommonImage image = imagesRepository.findById(name).orElseThrow(() -> new IllegalArgumentException("No such image"));
            Pair<String, byte[]> img = imagesMapper.mapToRawImage(image);
            return ResponseEntity.ok(img.getSecond());
        }
        catch (Exception exception) {
            log.warn("Error occurred", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
