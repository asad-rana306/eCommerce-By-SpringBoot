package com.dailycodework.dreamshops.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.PrivateKey;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private Object data;
}
