package com.team200.spyglassserver.domain.goal.DTOS;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class TargetAmountDTO {
    private String id;
    private Double start;
    private Double end;

    public TargetAmountDTO(String id, Double start, Double end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}
