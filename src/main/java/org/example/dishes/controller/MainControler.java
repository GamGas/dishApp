package org.example.dishes.controller;

import org.example.dishes.data.entity.UserItem;
import org.example.dishes.data.repository.DishRepository;
import org.example.dishes.data.repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class MainControler {

    @Autowired
    UserItemRepository userItemRepository;

    @Autowired
    DishRepository dishRepository;


    @GetMapping(value = {"/","/index"})
    public String index(Model model){
        model.addAttribute("message", "hello world");
        return  "index";
    }

    @GetMapping(value = { "/useritemlist" })
    public String userItemList(Model model) {


        model.addAttribute("useritems", userItemRepository.findAll());

        return "useritemlist";
    }

    @GetMapping(value = {"/adduser"})
    public String addUser(Model model){
        return "adduser";
    }
    @PostMapping(value = {"/adduser"})
    public String addNewUser(Model model,
                             @ModelAttribute("useritem") UserItem userItem){
        userItem.setLocalDate(LocalDate.now());
        userItemRepository.save(userItem);

        return "redirect:/useritemlist";
    }
}
