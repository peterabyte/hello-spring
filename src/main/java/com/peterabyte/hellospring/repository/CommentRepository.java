package com.peterabyte.hellospring.repository;

import com.peterabyte.hellospring.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByTopicId(Long topicId);
}
