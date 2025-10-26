package com.convergeX.technicalAssestement.technicalAssestement.dto.response_dto;


import com.convergeX.technicalAssestement.technicalAssestement.enums.StatusType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTaskDto {
    private String id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private StatusType statusType;
}
