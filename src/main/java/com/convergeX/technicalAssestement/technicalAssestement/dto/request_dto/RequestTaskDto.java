package com.convergeX.technicalAssestement.technicalAssestement.dto.request_dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestTaskDto {
    private String id;
    private String title;
    private String description;
}
