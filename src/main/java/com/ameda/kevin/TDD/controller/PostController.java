package com.ameda.kevin.TDD.controller;/*
*
@author ameda
@project TDD
@package com.ameda.kevin.TDD.controller
*
*/

import com.ameda.kevin.TDD.entity.Post;
import com.ameda.kevin.TDD.exception.PostNotFoundException;
import com.ameda.kevin.TDD.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("")
    public List<Post> findAll(){
        return postRepository.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Post> findById(@PathVariable("id") Integer id){
        return Optional.ofNullable(postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Post create(@RequestBody @Validated Post post){
        return postRepository.save(post);
    }
}
