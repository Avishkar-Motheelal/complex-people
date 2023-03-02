package com.example.complexpeople.controller;

import com.example.complexpeople.dto.AccessCardDTO;
import com.example.complexpeople.exception.PersonNotFoundException;
import com.example.complexpeople.model.AccessCard;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.service.AccessCardService;
import com.example.complexpeople.service.PeopleService;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Access Cards")
public class AccessCardController {
    private final AccessCardService accessCardService;
    private final PeopleService peopleService;


    @PostMapping(path = "/people/{peopleId}/accesscard")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "201", description = "Access card was created successfully"),
            @ApiResponse(responseCode = "404", description = "An invalid user was specified")
        }
    )
    public ResponseEntity<AccessCardDTO> createCard(@PathVariable int peopleId) throws PersonNotFoundException {
        Person person = peopleService.findById(peopleId);
        AccessCard accessCard = new AccessCard(person, true);
        accessCard = accessCardService.createOrUpdate(accessCard);
        return new ResponseEntity<>(new AccessCardDTO(accessCard), HttpStatus.CREATED);
    }


    @PostMapping(path = "/access/auth/")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Access granted"),
            @ApiResponse(responseCode = "403", description = "Access denied")
        }
    )
    public ResponseEntity<?> authorizeEntry(String accessCardId) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        AccessCard accessCard = accessCardService.getByCardId(accessCardId);

        if (accessCardService.isActiveCard(accessCard)) {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(status);
    }


    @DeleteMapping(path = "/people/accesscard")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "204", description = "Successfully disabled access card"),
            @ApiResponse(responseCode = "404", description = "Access card was not found")
        }
    )
    public ResponseEntity<?> disableCard(@RequestBody String body) {
        Gson gson = new Gson();
        Map<String, String> bodyMap = gson.fromJson(body, Map.class);
        accessCardService.disable(bodyMap.get("accessCardId"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(path = "/people/{peopleId}/accesscards")
    public ResponseEntity<List<AccessCardDTO>> getAllCards(@PathVariable int peopleId) {
        List<AccessCard> cards = accessCardService.getAllByPerson(peopleId).stream().filter(AccessCard::isActivated).toList();
        return new ResponseEntity<>(AccessCardDTO.fromAccessCardList(cards), HttpStatus.OK);
    }
}

