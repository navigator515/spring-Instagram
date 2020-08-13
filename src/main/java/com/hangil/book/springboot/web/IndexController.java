package com.hangil.book.springboot.web;

import com.hangil.book.springboot.config.auth.LoginUser;
import com.hangil.book.springboot.config.auth.dto.SessionUser;
import com.hangil.book.springboot.service.PostsService;
import com.hangil.book.springboot.web.dto.PostsResponseDto;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user)
    {
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";

    }

   @GetMapping("/posts/save")
   public String postsSave(){
      return "posts-save";    }



      @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
    PostsResponseDto dto=postsService.findById(id);
    model.addAttribute("post",dto);

    return "posts-update";
    }
}
