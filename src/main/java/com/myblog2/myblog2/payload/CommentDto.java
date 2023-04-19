package com.myblog2.myblog2.payload;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private String email;
    private String name;
    private String body;


}
