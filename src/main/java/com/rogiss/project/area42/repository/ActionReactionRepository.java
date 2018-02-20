package com.rogiss.project.area42.repository;

import com.rogiss.project.area42.model.ActionReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("actionReactionRepository")
public interface ActionReactionRepository extends JpaRepository<ActionReaction, Long> {

    ActionReaction findById(Long Id);
    List<ActionReaction> findByUserId(Integer Id);
}