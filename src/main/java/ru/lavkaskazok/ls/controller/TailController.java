package ru.lavkaskazok.ls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lavkaskazok.ls.dto.TailDto;
import ru.lavkaskazok.ls.model.Tail;
import ru.lavkaskazok.ls.service.TailService;

import javax.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/tale")
public class TailController {

    @Autowired
    private TailService tailService;

    @GetMapping("delete/{id}")
    public String deleteTail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("message", tailService.delete(id));
        return "result";
    }

    @PostMapping
    public String taleSave(@Valid TailDto tailDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            tailDto.setErrorMessage(String.valueOf(bindingResult.getFieldError()));
            model.addAttribute("tale", tailDto);
            return "tale";
        }
        if (tailDto.isStatus()) {
            tailService.edit(tailDto);
            List<Tail> tails = tailService.findAll();
            model.addAttribute("tales", tails);
        } else {
            try {
                tailService.add(tailDto);
                List<Tail> tails = tailService.findAll();
                model.addAttribute("tales", tails);
            } catch (Exception e) {
                tailDto.setErrorMessage(e.getMessage());
                model.addAttribute("tale", tailDto);
                return "tale";
            }

        }
        return "redirect:/tales";
    }

    @GetMapping("edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        if (tailService.checkById(id)) {
            Tail tail = (Tail) tailService.findById(id).get();
            TailDto tailDto = new TailDto();
            tailDto.setId(id);
            tailDto.setAnnonce(tail.getAnnonce());
            tailDto.setBody(tail.getBody());
            tailDto.setDate(tail.getDate());
            tailDto.setTitle(tail.getTitle());
            tailDto.setImageName(tail.getImage());
            tailDto.setStatus(true);
            model.addAttribute("tale", tailDto);
            return "tale";
        }
        return "redirect:/tales";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        TailDto tailDto = new TailDto();
        tailDto.setStatus(false);
        model.addAttribute("tale", tailDto);
        return "tale";
    }

    //TODO В дальнейшем сделать интерфейс для Mapping-а, как на вебинаре №42
    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        if (tailService.checkById(id)) {
            Tail tail = (Tail) tailService.findById(id).get();
            TailDto tailDto = new TailDto();
            tailDto.setAnnonce(tail.getAnnonce());
            tailDto.setBody(tail.getBody());
            tailDto.setDate(tail.getDate());
            tailDto.setTitle(tail.getTitle());
            tailDto.setImageName(tail.getImage());
            model.addAttribute("tale", tailDto);
            return "single";
        }
        return "redirect:/tales";
    }
}
