package com.abhilash.sb.assignment_w4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    private String name;
    private String email;
    private String password;
}
