package com.example.complexpeople.service;

import com.example.complexpeople.model.AccessHistory;
import com.example.complexpeople.repository.AccessHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessHistoryService {
    private final AccessHistoryRepository accessHistoryRepository;


    public List<AccessHistory> getHistoryByUserId(int userId) {
        return accessHistoryRepository.findAllByUserId(userId);
    }


    public AccessHistory create(AccessHistory accessHistory) {
        return accessHistoryRepository.save(accessHistory);
    }
}
