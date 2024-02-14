package com.ameda.kevin.TDD.entity.Gallery;/*
*
@author ameda
@project TDD
@package com.ameda.kevin.TDD.entity.Gallery
*
*/

public record Gallery(Integer id,
                      Integer albumId,
                      String title,
                      String url,
                      String thumbnailUrl) {
}
