package com.example.cassandrademo.repository;

import com.example.cassandrademo.tables.SecretImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretImagesRepository extends CrudRepository<SecretImage, String> {
}
