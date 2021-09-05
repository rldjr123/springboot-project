package com.web.project.springboot.Controller;

import com.web.project.springboot.config.auth.dto.LoginUser;
import com.web.project.springboot.config.auth.dto.SessionUser;
import com.web.project.springboot.dto.PostsResponseDto;
import com.web.project.springboot.posts.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class indexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    public indexController(PostsService postsService, HttpSession httpSession) {
        this.postsService = postsService;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        return "posts-update";
    }

}
