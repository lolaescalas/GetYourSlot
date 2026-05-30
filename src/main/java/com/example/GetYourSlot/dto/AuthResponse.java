package com.example.GetYourSlot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//what the API returns after the login
@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String role;
}