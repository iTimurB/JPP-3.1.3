package com.itimtim.springboot.springbootbootstrap.controller;

import com.itimtim.springboot.springbootbootstrap.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/home_page_for_all", method = RequestMethod.GET)
    public ModelAndView homePage(@AuthenticationPrincipal UserDetails authUser) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("accessWithUserRole", userService.getUserByNameWithRoles(authUser.getUsername()));
        modelAndView.setViewName("home_page_for_all");

        return modelAndView;
    }
}