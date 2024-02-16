package com.ameda.kevin.TDD.posts.controller;/*
*
@author ameda
@project TDD
@package com.ameda.kevin.TDD.posts
*
*/

import com.ameda.kevin.TDD.controller.PostController;
import com.ameda.kevin.TDD.entity.Post;
import com.ameda.kevin.TDD.exception.PostNotFoundException;
import com.ameda.kevin.TDD.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostRepository postRepository;
    private List<Post> posts = new ArrayList<>();
    @BeforeEach
    void setUp(){
        posts = List.of(
                new Post(1,1,"Hello","greeting message",null),
                new Post(2,2,"Bye","departure message",null)
        );
    }

    @Test
    void shouldFindAllPostTest() throws Exception {
            String jsonResponse = """
                [
                    {
                        "id":1,
                        "userId":1,
                        "title":"Hello",
                        "body":"greeting message",
                        "version": null
                    },
                    {
                        "id":2,
                        "userId":2,
                        "title":"Bye",
                        "body":"departure message",
                        "version": null
                    }
                ]
                """;
        when(postRepository.findAll()).thenReturn(posts);
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    // /api/posts/1
    @Test
    void shouldFindPostWhenValidId() throws Exception {
        when(postRepository.findById(1)).thenReturn(Optional.of(posts.get(0)));
        var post = posts.get(0);
        var json = """
        {
        "id": %s,
        "userId": %s,
        "title":"%s",
        "body":"%s",
        "version": null
         }
    """.formatted(post.id(), post.userId(), post.title(), post.body());

        mockMvc.perform(get("/api/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    void shouldNotFindPostWhenInvalidId() throws Exception {
        when(postRepository.findById(100))
                .thenThrow(PostNotFoundException.class);
        mockMvc.perform(get("/api/posts/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewPostWhenPostIsValid() throws Exception {
        var post  = new Post(
                4,4,"kevin","dev ameda",null
        );
        when(postRepository.save(post))
                .thenReturn(post);
        var json = """
        {
        "id": %s,
        "userId": %s,
        "title":"%s",
        "body":"%s",
        "version": null
         }
    """.formatted(post.id(), post.userId(), post.title(), post.body());
        mockMvc.perform(post("/api/posts")
                .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldNotCreatePostWhenPostIsInvalid() throws Exception {
        var post  = new Post(
                4,4,"","",null
        );
        when(postRepository.save(post))
                .thenReturn(post);
        var json = """
        {
        "id": %s,
        "userId": %s,
        "title":"%s",
        "body":"%s",
        "version": null
         }
    """.formatted(post.id(), post.userId(), post.title(), post.body());
        mockMvc.perform(post("/api/posts")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdatePostWhenGivenValidPost() throws Exception {
        Post updated =
                new Post(1,1,"new title","new body",1);
        when(postRepository.findById(1))
                .thenReturn(Optional.of(updated));
        when(postRepository.save(updated))
                .thenReturn(updated);
        var requestBody = """
        {
         "id": "%s",
         "userId": "%s",
         "title": "%s",
         "body": "%s",
         "version": "%s"
        }
        """.formatted(updated.id(), updated.userId(), updated.title(), updated.body(), updated.version());

        mockMvc.perform(put("/api/posts/1")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isOk());
    }
    @Test
    void shouldDeletePostWhenGivenValidId() throws Exception {
        doNothing().when(postRepository).deleteById(1);
        mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isNoContent());
        verify(postRepository,times(1))
                .deleteById(1);
    }
}
