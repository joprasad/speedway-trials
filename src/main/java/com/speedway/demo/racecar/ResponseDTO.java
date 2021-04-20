package com.speedway.demo.racecar;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
    String status;
    int status_code;
    Object data;
}
