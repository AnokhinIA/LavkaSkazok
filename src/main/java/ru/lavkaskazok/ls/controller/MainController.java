package ru.lavkaskazok.ls.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lavkaskazok.ls.model.Tail;
import ru.lavkaskazok.ls.service.TailService;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class MainController {

    @Autowired
    private TailService tailService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("content", "Некий контент");
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("content", "некий контент");
        return "about";
    }

    @GetMapping("/tales")
    public String tales(Model model) {
        List<Tail> tailsList = tailService.findAll();
        model.addAttribute("tails", tailsList);
        return "tales";
    }

}