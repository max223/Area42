package com.rogiss.project.area42.repository.ActionsReactions;


import com.rogiss.project.area42.model.Action;
import com.rogiss.project.area42.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Long> {

    Reaction findReactionById(Long id);
//    List<Reaction>find
    List<Reaction> findReactionsByCompatibleActions(Action action);
}
