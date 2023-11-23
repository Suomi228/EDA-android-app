package com.example.eda.uretrofit.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuth {
    private String email;
    private String password;
}
