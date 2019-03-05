package com.example.springdemo.controller;

import com.example.springdemo.model.User;
import com.example.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    //@RequestMapping(value = "/" , method = RequestMethod.GET)
    public String main() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(ModelMap modelMap) {
        List <User> all = userRepository.findAll();
        modelMap.addAttribute("users", all);
        return "home";
    }
}
