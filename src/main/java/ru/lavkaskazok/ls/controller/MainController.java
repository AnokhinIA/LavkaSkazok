package ru.lavkaskazok.ls.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lavkaskazok.ls.service.TailService;

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
    public String tales(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                        @RequestParam(value = "size", required = false, defaultValue = "3") int size, Model model) {
        if (pageNumber <= 0) pageNumber = 1;
        model.addAttribute("tales", tailService.getPage(pageNumber, size));
        return "tales";
    }

    @GetMapping("/shedule")
    public String shedule(Model model) {
        model.addAttribute("content", "некий контент");
        return "shedule";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("content", "некий контент");
        return "contact";
    }


}