package com.example.complexpeople.dto;

import com.example.complexpeople.model.AccessCard;
import com.example.complexpeople.model.AccessHistory;
import com.example.complexpeople.model.User;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Data
public class AccessHistoryDTO {
    private UUID accessCardId;
    private String dateTime;
    private UserResponseDTO user;


    public AccessHistoryDTO(AccessHistory accessHistory) {
        User user = accessHistory.getAccessCard().getUser();
        AccessCard accessCard = accessHistory.getAccessCard();
        this.accessCardId = accessCard.getAccessCardId();
        this.user = new UserResponseDTO(user);
        this.dateTime = accessHistory.getDateUsed().format(DateTimeFormatter.ISO_DATE_TIME);
    }


    public static List<AccessHistoryDTO> fromAccessHistoryList(List<AccessHistory> accessHistoryList) {
        return accessHistoryList.stream().map(AccessHistoryDTO::new).toList();
    }
}
