package com.abhilash.sb.assignment_w4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsDTO {
    private Long id;
    private String name;
    private String title;
}
