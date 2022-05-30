package ru.lavkaskazok.ls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.lavkaskazok.ls.dto.EventDto;
import ru.lavkaskazok.ls.model.Event;
import ru.lavkaskazok.ls.paging.Paged;
import ru.lavkaskazok.ls.paging.Paging;
import ru.lavkaskazok.ls.repository.EventRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Optional<?> findById(Long id) {
        if (eventRepository.findById(id).isPresent()) {
            return eventRepository.findById(id);
        } else {
            return Optional.of(new Event());
        }
    }

    @Transactional
    public String delete(Long id) {
        if (checkById(id)) {
            try {
                eventRepository.deleteById(id);
                return "Удаление мероприятия прошло успешно";
            } catch (Exception e) {
                return "Операция удаления не удалась. Полный код ошибки: " + e.getMessage();
            }
        }
        return "Такой записи нет";
    }

    @Transactional
    public void add(EventDto eventDto) {
        Event event = new Event();
        event.setAnnonce(eventDto.getAnnonce());
        event.setReport(eventDto.getReport());
        event.setDate(eventDto.getDate());
        event.setTitle(eventDto.getTitle());
        event.setAddress(eventDto.getAddress());
        event.setType(eventDto.getType());
        event.setImageName(event.getImageName());
        eventRepository.save(event);
    }

    @Transactional
    public void edit(EventDto eventDto) {
        Event event = eventRepository.findById(eventDto.getId()).get();
        event.setAnnonce(eventDto.getAnnonce());
        event.setReport(eventDto.getReport());
        event.setDate(eventDto.getDate());
        event.setTitle(eventDto.getTitle());
        event.setAddress(eventDto.getAddress());
        event.setType(eventDto.getType());
        eventRepository.save(event);
    }

    public boolean checkById(long id) {
        return eventRepository.existsById(id);
    }

    public Paged<Event> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Event> postPage = eventRepository.findAll(request); //new Sort(Sort.Direction.ASC, "id") Сортировка не работает
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }
}
