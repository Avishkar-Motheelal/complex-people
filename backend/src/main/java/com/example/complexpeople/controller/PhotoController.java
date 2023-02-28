package com.example.complexpeople.controller;


import com.example.complexpeople.dto.PhotoDTO;
import com.example.complexpeople.model.Photo;
import com.example.complexpeople.service.PhotoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService photoService;

    @ApiResponse(responseCode = "200", description = "A photo was successfully uploaded")
    @PostMapping
    public Photo uploadPhoto(@RequestBody PhotoDTO photoDTO) {

        Photo photo = new Photo();
        photo.setPhotoUrl(photoDTO.getPhotoUrl());

        photo = photoService.upload(photo);
        return photo;

    }
}
