package com.ameda.kevin.TDD.controller;/*
*
@author ameda
@project TDD
@package com.ameda.kevin.TDD.controller
*
*/
import com.ameda.kevin.TDD.entity.Gallery.Gallery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/galleries")
public class GalleryController {

    @GetMapping
    public List<Gallery> allGallery(){
       List<Gallery> galleries = new ArrayList<>();
       galleries.add(new Gallery(1,100,"Trip to Hamburg","https://www.inst.com/hamburg","hamburg"));
       galleries.add(new Gallery(2,101,"Trip to Berlin","https://www.inst.com/berlin","berlin"));
       galleries.add(new Gallery(3,102,"Trip to Cologne","https://www.inst.com/cologne","cologne"));
//       galleries.add(new Gallery(4,103,"Trip to Strutsgart","https://www.inst.com/strutsgart","strutsgart"));
        return galleries;
    }
}
