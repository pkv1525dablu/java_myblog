package com.myblog2.myblog2.service;


import com.myblog2.myblog2.payload.PostDto;
import com.myblog2.myblog2.service.impl.PostResponse;

import java.util.List;

public interface PostService {
      PostDto createPost(PostDto postDto);

      PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

      PostDto getPostById(Long id);

      PostDto updatePostById(PostDto postDto, Long id);

      void deletePost(Long id);
}
