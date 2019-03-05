package com.example.springdemo.controller;

import com.example.springdemo.model.User;
import com.example.springdemo.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/home";
    }

    @PostMapping("/user/add")
    public String addUserPost(@ModelAttribute User user, @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        String name = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File file = new File("C:\\Users\\User\\IdeaProjects\\springdemo\\picture\\" + name);
        multipartFile.transferTo(file);
        user.setPicUrl(name);
        userRepository.save(user);
        return "redirect:/home";
    }

    @GetMapping("/user/add")
    public String addUserGet() {
        return "addUser";
    }

    @GetMapping("/getImage")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("picUrl") String picUrl) throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\User\\IdeaProjects\\springdemo\\picture\\" + picUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

}
