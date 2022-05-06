package ru.lavkaskazok.ls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lavkaskazok.ls.dto.TailDto;
import ru.lavkaskazok.ls.model.Tail;
import ru.lavkaskazok.ls.service.TailService;

@Controller
@RequestMapping("/tail")
public class TailController {

    @Autowired
    private TailService tailService;

    @DeleteMapping("/{id}")
    public void deleteTail(@PathVariable("id") String id){
    }

    //TODO В дальнейшем сделать интерфейс для Mapping-а, как на вебинаре №42
    @GetMapping("/{id}")
    public String view (@PathVariable("id") String id, Model model){
        long idTail = Long.parseLong(id);
        if (tailService.checkById(idTail)) {
            Tail tail  = (Tail) tailService.findById(idTail).get();
            TailDto tailDto = new TailDto();
            tailDto.setAnnonce(tail.getAnnonce());
            tailDto.setBody(tail.getBody());
            tailDto.setDate(tail.getDate());
            tailDto.setTitle(tail.getTitle());
            tailDto.setImage(tail.getImage());
            model.addAttribute("tail",tailDto);
            return "single";
        }
        return "redirect:/tales";
    }


}
