package com.rogiss.project.area42.service.ActionsReactions;

import com.rogiss.project.area42.model.ActionReaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActionReactionService {

    List<ActionReaction> findAll();

    List<ActionReaction> findByUserId(Long userId);

    ActionReaction findOne(Long id);

    ActionReaction saveActionReaction(ActionReaction actionReaction);

    void deleteActionReaction(Long id);
}
