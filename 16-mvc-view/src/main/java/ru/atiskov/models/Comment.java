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
public class Comment {

    @Id
    @GeneratedValue
    private long commentId;

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
