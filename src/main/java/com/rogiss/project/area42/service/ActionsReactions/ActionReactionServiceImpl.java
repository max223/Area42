package com.rogiss.project.area42.service.ActionsReactions;

import com.rogiss.project.area42.model.ActionReaction;
import com.rogiss.project.area42.repository.ActionsReactions.ActionReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionReactionServiceImpl implements ActionReactionService {

    @Autowired
    ActionReactionRepository actionReactionRepository;

    @Override
    public List<ActionReaction> findAll() {
        return actionReactionRepository.findAll();
    }

    @Override
    public List<ActionReaction> findByUserId(Long userId) {
        return actionReactionRepository.findByUserId(userId);
    }

    @Override
    public ActionReaction findOne(Long id) {
        return actionReactionRepository.findOne(id);
    }

    @Override
    public ActionReaction saveActionReaction(ActionReaction actionReaction) {
         actionReactionRepository.save(actionReaction);
        return actionReaction;
    }

    @Override
    public void deleteActionReaction(Long id) {
        actionReactionRepository.delete(id);
    }
}
