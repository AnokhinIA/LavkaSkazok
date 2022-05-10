package ru.lavkaskazok.ls.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="tales")
public class Tail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    //private String date;
    private String title;
    @Type(type="text")
    private String annonce;
    @Type(type="text")
    private String body;
    private String image;
}
