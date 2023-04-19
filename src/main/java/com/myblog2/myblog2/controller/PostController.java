package com.myblog2.myblog2.controller;

import com.myblog2.myblog2.payload.PostDto;
import com.myblog2.myblog2.service.PostService;
import com.myblog2.myblog2.service.impl.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    // http://localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result){
       if(result.hasErrors()){
return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
        PostDto dto = postService.createPost(postDto);
          return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping()    // get all data   // http://localhost:8080/api/posts2?pageNo=0&pageSize=5&sortBy=id&sortDir=asc
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir){
        return postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
    }


    @GetMapping("/{id}")  //http://localhost:8080/api/posts2/1
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id")Long id){
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")   // Update    //http://localhost:8080/api/posts2/1
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,
                                                  @PathVariable("id")Long id){
     PostDto post= postService.updatePostById(postDto,id);
          return  ResponseEntity.ok(post);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id")Long id){
        postService.deletePost(id);
        return new ResponseEntity<String>("Post id Deleted",HttpStatus.OK);
    }

}
