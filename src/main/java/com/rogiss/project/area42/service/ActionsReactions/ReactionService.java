package com.rogiss.project.area42.service.ActionsReactions;

import com.rogiss.project.area42.model.Action;
import com.rogiss.project.area42.model.Reaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ReactionService {

    List<Reaction> findAll();

    Reaction findReactionById(Long id);

    Reaction saveReaction(Reaction reaction);

    void deleteReaction(Long id);



    List<Reaction> findReactionsByCompatibleActions(Action action);


}
