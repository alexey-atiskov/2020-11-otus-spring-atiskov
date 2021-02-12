package ru.atiskov.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import ru.atiskov.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
