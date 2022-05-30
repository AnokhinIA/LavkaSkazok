package ru.lavkaskazok.ls.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
public class EventDto {
    private Long id;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate date;
    @NonNull
    @Size(max = 100)
    private String title;
    @NonNull @NotBlank @Size(max=500)
    private String annonce;
    @NonNull @NotBlank @Size(max=100)
    private String address;
    @NonNull @NotBlank @Size(max=50)
    private String type;
    private String report;
    private String imageName;
    private String errorMessage;
}
