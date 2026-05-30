package com.example.GetYourSlot.dto;

import lombok.Data;

//what the client sends to register
@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}