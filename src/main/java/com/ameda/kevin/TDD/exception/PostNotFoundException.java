package com.ameda.kevin.TDD.exception;/*
*
@author ameda
@project TDD
@package com.ameda.kevin.TDD.exception
*
*/

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException{
}
