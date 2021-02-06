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
public class Comment {

    @Id
    private String commentId;

    private String value;

    public Comment(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", value='" + value + '\'' +
                '}';
    }
}
