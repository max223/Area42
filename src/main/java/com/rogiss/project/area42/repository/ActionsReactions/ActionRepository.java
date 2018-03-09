package com.rogiss.project.area42.repository.ActionsReactions;

import com.rogiss.project.area42.model.Action;
import com.rogiss.project.area42.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    Action findActionById(Long Id);
}
