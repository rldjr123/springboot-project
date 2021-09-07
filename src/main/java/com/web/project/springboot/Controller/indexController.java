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

        //@LoginUser 어노테이션으로 수정함, Session에 경우 많은메소드에서 호출할텐데 똑같은 코드가 반복될것이고 수정할사항이 생기면 찾아서 전부 수정해야함
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        
        
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
