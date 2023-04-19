package com.myblog2.myblog2.payload;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PostDto {

    private Long id;
    @NotEmpty
    @Size(min=2,message="title at least 2 char")
    private String title;
    @NotEmpty
    @Size(min=2,message="desc at least 2 char")
    private String description;
    private String content;
    private List<CommentDto> comments;

    // constructors, getters, and setters

}
