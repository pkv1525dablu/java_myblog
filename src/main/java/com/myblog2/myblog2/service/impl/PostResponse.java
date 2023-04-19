package com.myblog2.myblog2.service.impl;

import com.myblog2.myblog2.payload.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDto> content;
    private int PageNo;
    private int PageSize;
    private int totalPage;
    private int totalElement;
    private boolean isLast;
}
