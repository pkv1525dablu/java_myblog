package com.myblog2.myblog2.controller;

import com.myblog2.myblog2.payload.CommentDto;
import com.myblog2.myblog2.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments") // localhost:8090/api/posts/2/comments
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments") //localhost:8090/api/posts/1/comments
    public List<CommentDto> getCommentByPostId(@PathVariable("postId") long postId) {
        return commentService.getCommentByPostId(postId);

    }

    @GetMapping("/{postId}/comments/{id}")  //localhost:8090/api/posts/1/comments/1
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable("postId") long postId,
            @PathVariable("id") long commentId) {

        CommentDto commentById = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentById, HttpStatus.OK);

    }

}