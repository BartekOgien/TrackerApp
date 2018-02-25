package com.tracker.repository;

import com.tracker.model.domain.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaryDao extends JpaRepository<Commentary, Integer> {
}
