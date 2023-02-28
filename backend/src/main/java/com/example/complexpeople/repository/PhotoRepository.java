package com.example.complexpeople.repository;

import com.example.complexpeople.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    Photo getPhotoByPhotosId(int id);
}
