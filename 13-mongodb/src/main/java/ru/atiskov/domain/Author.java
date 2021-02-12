package ru.atiskov.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "authors")
public class Author {
    @Id
    private String authId;

    private String firstName;

    private String lastName;

    private String secondaryName;

    public Author(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authId=" + authId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondaryName='" + secondaryName + '\'' +
                '}';
    }
}
