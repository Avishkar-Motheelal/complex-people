package com.example.complexpeople.service;

import com.example.complexpeople.model.Photo;
import com.example.complexpeople.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public Photo upload(Photo photo) {return photoRepository.save(photo);}
}
