package com.abhilash.sb.assignment_w4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDTO {
    private String email;
    private String password;
}
