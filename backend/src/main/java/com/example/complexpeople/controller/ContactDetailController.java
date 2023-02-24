package com.example.complexpeople.controller;

import com.example.complexpeople.service.ContactDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Contact Details")
public class ContactDetailController {
    private final ContactDetailService contactDetailService;
}
