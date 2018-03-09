package com.rogiss.project.area42.repository.ActionsReactions;

import com.rogiss.project.area42.model.ActionReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ActionReactionRepository extends JpaRepository<ActionReaction, Long> {

    ActionReaction findActionReactionById(Long Id);
    List<ActionReaction> findByUserId(Long Id);

}