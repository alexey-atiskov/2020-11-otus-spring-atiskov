package ru.atiskov.repositories;

import org.springframework.data.repository.CrudRepository;

import ru.atiskov.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
