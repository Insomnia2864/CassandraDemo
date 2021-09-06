package com.example.cassandrademo.repository;

import com.example.cassandrademo.tables.CommonImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonImageRepository extends CrudRepository<CommonImage, String> {
}
