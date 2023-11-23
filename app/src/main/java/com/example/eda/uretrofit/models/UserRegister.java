package com.example.eda.uretrofit.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegister {
    private String name;
    private String surname;
    private String email;
    private String password;
}
