package ru.lavkaskazok.ls.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class TailDto {
    private Long id;
    private Date date;
    private String title;
    private String annonce;
    private String body;
    private String image;

}
