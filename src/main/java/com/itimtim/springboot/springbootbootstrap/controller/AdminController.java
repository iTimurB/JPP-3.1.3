package com.itimtim.springboot.springbootbootstrap.controller;

import com.itimtim.springboot.springbootbootstrap.model.User;
import com.itimtim.springboot.springbootbootstrap.service.RoleService;
import com.itimtim.springboot.springbootbootstrap.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/list")
    public ModelAndView printAllUsers(@AuthenticationPrincipal UserDetails authUser) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("userList", userService.getAllUsers());
        modelAndView.addObject("userCurrent", userService.getUserByNameWithRoles(authUser.getUsername()));
        modelAndView.setViewName("adminPage");
        return modelAndView;
    }

    @PostMapping(value = "/users/edit")
    public ModelAndView editUser(User user, @RequestParam("editUserRoleId") List<Long> editUserRoleId) {
        ModelAndView modelAndView = new ModelAndView();

        user.setRoles(roleService.getRolesByIds(editUserRoleId));
        userService.update(user.getId(), user);
        modelAndView.setViewName("redirect:/admin/list");
        return modelAndView;
    }

    @PostMapping(value = "/users/add")
    public ModelAndView saveUser(User user, @RequestParam("newUserRoleId") List<Long> newUserRoleId) {
        ModelAndView modelAndView = new ModelAndView();

        user.setRoles(roleService.getRolesByIds(newUserRoleId));;
        userService.save(user);
        modelAndView.setViewName("redirect:/admin/list");
        return modelAndView;
    }

    @GetMapping(value = "/users/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") long removableUserId) {
        ModelAndView modelAndView = new ModelAndView();

        userService.delete(removableUserId);
        modelAndView.setViewName("redirect:/admin/list");
        return modelAndView;
    }


}