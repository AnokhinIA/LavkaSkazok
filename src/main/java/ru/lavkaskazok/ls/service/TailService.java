package ru.lavkaskazok.ls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.lavkaskazok.ls.paging.Paged;
import ru.lavkaskazok.ls.paging.Paging;
import ru.lavkaskazok.ls.dto.TailDto;
import ru.lavkaskazok.ls.model.Tail;
import ru.lavkaskazok.ls.repository.TaleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TailService {

    @Autowired
    private TaleRepository taleRepository;

    public List<Tail> findAll() {
        return taleRepository.findAll();
    }

    public Optional<?> findById(Long id) {
        if (taleRepository.findById(id).isPresent()) {
            return taleRepository.findById(id);
        } else {
            return Optional.of(new Tail());
        }
    }

    @Transactional
    public String delete(Long id) {
        if (checkById(id)) {
            try {
                taleRepository.deleteById(id);
                return "Удаление сказки прошло успешно";
            } catch (Exception e) {
                return "Операция удаления не удалась. Полный код ошибки: " + e.getMessage();
            }
        }
        return "Такой записи нет";
    }

    @Transactional
    public void add(TailDto tailDto) {
        Tail tail = new Tail();
        tail.setAnnonce(tailDto.getAnnonce());
        tail.setBody(tailDto.getBody());
        tail.setDate(tailDto.getDate());
        tail.setTitle(tailDto.getTitle());
        tail.setImage(tail.getImage());
        taleRepository.save(tail);
    }

    @Transactional
    public void edit(TailDto tailDto) {
        Tail tail = taleRepository.findById(tailDto.getId()).get();
        tail.setAnnonce(tailDto.getAnnonce());
        tail.setBody(tailDto.getBody());
        tail.setDate(tailDto.getDate());
        tail.setTitle(tailDto.getTitle());
        tail.setImage(tail.getImage());
        taleRepository.save(tail);
    }

    public boolean checkById(long id) {
        return taleRepository.existsById(id);
    }

    public Paged<Tail> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Tail> postPage = taleRepository.findAll(request); //new Sort(Sort.Direction.ASC, "id") Сортировка не работает
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }
}
