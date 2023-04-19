package com.myblog2.myblog2.service.impl;


import com.myblog2.myblog2.entity.Comment;
import com.myblog2.myblog2.entity.Post;
import com.myblog2.myblog2.exception.BlogAPIException;
import com.myblog2.myblog2.exception.ResourceNotFoundException;
import com.myblog2.myblog2.payload.CommentDto;
import com.myblog2.myblog2.repository.CommentRepository;
import com.myblog2.myblog2.repository.PostRepository;
import com.myblog2.myblog2.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }


    @Override        // get comment By PostId
    public List<CommentDto> getCommentByPostId(long PostId) {
        List<Comment> byPostId = commentRepository.findByPostId(PostId);
        return byPostId.stream().map(i->mapTODto(i)).collect(Collectors.toList());
    }

    @Override   //get Comemnt one at a time
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("id not found ","id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found id", "id", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw  new BlogAPIException("Commet not found", HttpStatus.BAD_REQUEST);
        }
         commentRepository.findById(commentId);
        return mapTODto(comment);
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("no record found"));

        Comment comment = mapTOEntity(commentDto);
        comment.setPost(post);
        Comment save = commentRepository.save(comment);

        CommentDto commentDto1 = mapTODto(save);
        return commentDto1;
    }



    Comment mapTOEntity(CommentDto commentDto) {
        Comment comment=mapper.map(commentDto,Comment.class);
        return comment;

    //        Comment comment = new Comment();
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
//        comment.setName(commentDto.getName());
//        return comment;
    }

    CommentDto mapTODto(Comment comment) {
CommentDto dto=mapper.map(comment,CommentDto.class);
return  dto;

    //        CommentDto dto = new CommentDto();
//        dto.setId(comment.getId());
//        dto.setBody(comment.getBody());
//        dto.setEmail(comment.getEmail());
//        dto.setName(comment.getName());
//        return dto;

    }
}
