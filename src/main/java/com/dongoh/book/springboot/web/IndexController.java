package com.dongoh.book.springboot.web;

import com.dongoh.book.springboot.config.auth.LoginUser;
import com.dongoh.book.springboot.config.auth.dto.SessionUser;
import com.dongoh.book.springboot.service.PostsService;
import com.dongoh.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts",postsService.findAllDesc());

        if(user!=null)
            model.addAttribute("userName",user.getName());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave(Model model, @LoginUser SessionUser user)
    {
        if(user!=null)
            model.addAttribute("userName",user.getName());

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user)
    {
        PostsResponseDto dto=postsService.findById(id);
        model.addAttribute("post",dto);

        if(user!=null)
            model.addAttribute("userName",user.getName());



        return "posts-update";
    }
}
