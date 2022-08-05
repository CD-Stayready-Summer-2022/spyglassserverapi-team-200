package com.team200.spyglassserver.domain.goal.DTOS;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StatusDTO {
    private String id;
    private String statusString;

    public StatusDTO() {
    }

    public StatusDTO(String id, String statusString) {
        this.id = id;
        this.statusString = statusString;
    }
}
