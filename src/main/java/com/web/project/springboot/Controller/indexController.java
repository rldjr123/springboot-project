package com.web.project.springboot.Controller;

import com.web.project.springboot.posts.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    private final PostsService postsService;

    public indexController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postssave(){
        return "posts-save";
    }

}
