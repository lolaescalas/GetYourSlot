package com.example.GetYourSlot.dto;

import lombok.Data;

//what the client sends to login
@Data
public class AuthRequest {
    private String email;
    private String password;
}