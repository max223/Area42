package com.rogiss.project.area42.service.ActionsReactions;

import com.rogiss.project.area42.model.Action;
import com.rogiss.project.area42.model.Reaction;
import com.rogiss.project.area42.repository.ActionsReactions.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    ReactionRepository reactionRepository;

    @Override
    public List<Reaction> findAll() {
        return reactionRepository.findAll();
    }

    @Override
    public Reaction findReactionById(Long id) {
        return reactionRepository.findReactionById(id);
    }

    @Override
    public Reaction saveReaction(Reaction reaction) {
        reactionRepository.save(reaction);
        return reaction;
    }

    @Override
    public void deleteReaction(Long id) {
        reactionRepository.delete(id);
    }

    @Override
    public Reaction updateReaction(Reaction oldreaction) {

        Reaction toUpdate = reactionRepository.findReactionById(oldreaction.getId());
        toUpdate.setCompatibleActions(oldreaction.getCompatibleActions());
        toUpdate.setDescription(oldreaction.getDescription());
        toUpdate.setId(oldreaction.getId());
        reactionRepository.save(toUpdate);
        return toUpdate;
    }

    @Override
    public List<Reaction> findReactionsByCompatibleActions(Action action) {
        return reactionRepository.findReactionsByCompatibleActions(action);
    }
}
