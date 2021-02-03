package ru.atiskov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.atiskov.models.Comment;

public interface CommentRepositoryJpa extends JpaRepository<Comment, Long> {
}
