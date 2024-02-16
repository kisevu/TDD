package com.ameda.kevin.TDD.entity;/*
*
@author ameda
@project TDD
@package com.ameda.kevin.TDD.entity
*
*/

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Post(
        @Id
        Integer id,
        Integer userId,
        String title,
        String body,
        @Version
        Integer version) {
}
