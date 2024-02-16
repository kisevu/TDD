package com.ameda.kevin.TDD.data;/*
*
@author ameda
@project TDD
@package com.ameda.kevin.TDD.data
*
*/

import com.ameda.kevin.TDD.entity.Posts;
import com.ameda.kevin.TDD.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


@Component
public class PostDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(PostDataLoader.class);
    private final ObjectMapper objectMapper;
    private final PostRepository postRepository;
    public PostDataLoader(ObjectMapper objectMapper, PostRepository postRepository) {
        this.objectMapper = objectMapper;
        this.postRepository = postRepository;
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
    /*
    @author{author}
    */
        if(postRepository.count() == 0){
            String POSTS_JSON = "/data/posts.json";
            log.info("Loading posts into dB from json: {}",POSTS_JSON);
            try(InputStream inputStream = TypeReference.class.getResourceAsStream(POSTS_JSON)){
                Posts response = objectMapper.readValue(inputStream, Posts.class);
                postRepository.saveAll(response.posts());
            }catch(IOException ex){
                throw new RuntimeException("Failed to read json data",ex);
            }
        }
    }
}
