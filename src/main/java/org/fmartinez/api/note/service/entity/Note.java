package org.fmartinez.api.note.service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
@Builder
@Document("note")
public class Note {
    @MongoId
    private String id;
    @NotBlank(message = "field user cannot be blank")
    private String user;
    @NotBlank(message = " field title cannot be blank")
    private String title;
    @NotBlank(message = "field note cannot be blank")
    private String note;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;


    public Note() {
        this.updated = LocalDateTime.now();
        this.created = LocalDateTime.now();
    }

}
