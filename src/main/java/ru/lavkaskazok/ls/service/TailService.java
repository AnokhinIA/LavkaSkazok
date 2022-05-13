package ru.lavkaskazok.ls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.lavkaskazok.ls.paging.Paged;
import ru.lavkaskazok.ls.paging.Paging;
import ru.lavkaskazok.ls.dto.TailDto;
import ru.lavkaskazok.ls.model.Tail;
import ru.lavkaskazok.ls.repository.TailRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TailService {

    @Autowired
    private TailRepository tailRepository;

    public List<Tail> findAll() {
        List<Tail> tailsList = tailRepository.findAll();
        return tailsList;
    }

    public Optional<?> findById(Long id) {
        Optional<Tail> insuranceType = tailRepository.findById(id);
        return insuranceType;
    }

    @Transactional
    public void delete(Long id) {
        tailRepository.deleteById(id);
    }

    @Transactional
    public void add(TailDto tailDto) {
        Tail tail = new Tail();
        tail.setAnnonce(tailDto.getAnnonce());
        tail.setBody(tailDto.getBody());
        tail.setDate(tailDto.getDate());
        tail.setTitle(tailDto.getTitle());
        tail.setImage(tail.getImage());
        tailRepository.save(tail);
    }

    @Transactional
    public void edit(TailDto tailDto) {
        Tail tail = (Tail) tailRepository.findById(tailDto.getId()).get();
        tail.setAnnonce(tailDto.getAnnonce());
        tail.setBody(tailDto.getBody());
        tail.setDate(tailDto.getDate());
        tail.setTitle(tailDto.getTitle());
        tail.setImage(tail.getImage());
        tailRepository.save(tail);
    }

    public boolean checkById(long id) {
        boolean enable = tailRepository.existsById(id);
        return enable;
    }

    public Paged<Tail> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Tail> postPage = tailRepository.findAll(request); //new Sort(Sort.Direction.ASC, "id") Сортировка не работает
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }

}
