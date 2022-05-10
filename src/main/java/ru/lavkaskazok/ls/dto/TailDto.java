package ru.lavkaskazok.ls.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class TailDto {
    private Long id;

    /* В связи с проблемами десериализации даты пока ставлю значание String*/
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private Date date;
    //private String date;
    private String title;
    @NonNull
    private String annonce;
    @NonNull
    private String body;
    private String imageName;
    //Значения статуса 0 - создать новую запись, 1 - редактировать
    private boolean status;
    private String errorMessage;
}
