package ru.atiskov.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "books")
public class Genre {

    @Id
    private String genId;

    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
