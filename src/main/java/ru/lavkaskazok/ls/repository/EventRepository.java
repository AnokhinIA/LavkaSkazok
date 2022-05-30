package ru.lavkaskazok.ls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lavkaskazok.ls.model.Event;


public interface EventRepository extends JpaRepository<Event, Long> {
}
