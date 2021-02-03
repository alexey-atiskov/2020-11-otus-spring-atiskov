package ru.atiskov.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Genre {

    @Id
    @GeneratedValue
    private long genId;

    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
