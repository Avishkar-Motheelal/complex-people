package com.example.complexpeople.service;

import com.example.complexpeople.exception.NotFoundException;
import com.example.complexpeople.model.AccessCard;
import com.example.complexpeople.repository.AccessCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccessCardService {
    private final AccessCardRepository accessCardRepository;


    public AccessCard createOrUpdate(AccessCard accessCard) {
        return accessCardRepository.save(accessCard);
    }


    public AccessCard getById(String accessCardId) {
        UUID id;
        try {
            id = UUID.fromString(accessCardId);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Access card with specified ID was not found");
        }
        return accessCardRepository.findById(id).orElseThrow(() -> new NotFoundException("Access card with specified ID was not found"));
    }


    public boolean isActiveCard(AccessCard accessCard) {
        return accessCard.isActivated();
    }


    public void disable(String accessCardId) {
        AccessCard accessCard = getById(accessCardId);
        accessCard.setActivated(false);
        createOrUpdate(accessCard);
    }


    public List<AccessCard> getAllByUser(int userId) {
        return accessCardRepository.findByUserUserId(userId);
    }
}
