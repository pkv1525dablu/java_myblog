package com.myblog2.myblog2.service;

import com.myblog2.myblog2.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto>getCommentByPostId(long postId);
    CommentDto getCommentById(long postId,long commentId);
}
