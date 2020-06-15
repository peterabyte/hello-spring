package com.peterabyte.hellospring.repository;

import com.peterabyte.hellospring.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    public Topic findBySlug(String slug);
}
