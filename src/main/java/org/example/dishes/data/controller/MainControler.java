package org.example.dishes.data.controller;

import org.example.dishes.data.entity.UserItem;
import org.example.dishes.data.repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainControler {

    @Autowired
    UserItemRepository userItemRepository;


    @GetMapping(value = {"/","/index"})
    public String index(Model model){
        model.addAttribute("message", "hello world");
        return  "index";
    }

    @GetMapping(value = { "/useritemlist" })
    public String personList(Model model) {

        UserItem item = new UserItem();
        item.setLocalDate(LocalDate.now());
        item.setUsername("Smurph");
        item.setPassword("Password");
        List<UserItem> useritems = new ArrayList<>();
        useritems.add(item);
        model.addAttribute("useritems", useritems);

        return "useritemlist";
    }
}
