package ru.lavkaskazok.ls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lavkaskazok.ls.model.Tail;


public interface TailRepository extends JpaRepository<Tail, Long> {
}
