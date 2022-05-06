package ru.lavkaskazok.ls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lavkaskazok.ls.model.Tail;
import ru.lavkaskazok.ls.service.TailService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TailService tailService;

    @GetMapping
    public String admin(Model model) {
        List<Tail> tailsList = tailService.findAll();
        model.addAttribute("tails", tailsList);
        return "admin";
    }

 }
