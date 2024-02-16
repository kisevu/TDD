package com.ameda.kevin.TDD.repository;/*
*
@author ameda
@project TDD
@package com.ameda.kevin.TDD.repository
*
*/

import com.ameda.kevin.TDD.entity.Post;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ListCrudRepository<Post,Integer> {
}
