package com.convergeX.technicalAssestement.technicalAssestement.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StandardResponseDto {
    private int code;
    private String message;
    private Object data;


    public StandardResponseDto(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

