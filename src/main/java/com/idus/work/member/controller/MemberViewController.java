package com.idus.work.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view/member")
public class MemberViewController {

    @RequestMapping(value="/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value="/create")
    public String create() {
        return "/member/create";
    }

    @RequestMapping(value="/list")
    public String list() {
        return "/member/list";
    }

    @GetMapping(value="/detail/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("id", id);
        mv.setViewName("/member/detail");
        return mv;
    }
}
