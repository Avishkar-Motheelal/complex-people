package com.example.complexpeople.controller;

import com.example.complexpeople.dto.NewPersonDTO;
import com.example.complexpeople.exception.VisitNotFoundException;
import com.example.complexpeople.model.Visit;
import com.example.complexpeople.service.VisitService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/visit")
@CrossOrigin(origins = "http://localhost:4200")
public class VisitController {

    private final VisitService visitService;
    @ApiResponse(responseCode = "200", description = "Returns a list of all visits")
    @ApiResponse(responseCode = "404", description = "No visitors in database")
    @GetMapping
    public Iterable<Visit> getAllVisits() {
        return visitService.getAll();
    }

    @ApiResponse(responseCode = "200", description = "Returns visit found by id")
    @GetMapping("/{id}")
    public Visit getVisitById(@PathVariable Integer id) throws VisitNotFoundException {
        return visitService.getVisitById(id);
    }


    @ApiResponse(responseCode = "200", description = "A visit was created in the database")
    @ApiResponse(responseCode = "500", description = "Invalid data format")
    @PostMapping
    public Visit createVisit(@Valid @RequestBody NewPersonDTO newVisitorDTO, int apartmentId, int photoId){

       return visitService.addVisit(newVisitorDTO, apartmentId, photoId);

    }

    @ApiResponse(responseCode = "200", description = "Visitor date out insert")
    @PatchMapping("/{id}")
    public Visit updateVisit(@PathVariable Integer id)
            throws VisitNotFoundException {

        return visitService.updateVisit(id);
    }
}
