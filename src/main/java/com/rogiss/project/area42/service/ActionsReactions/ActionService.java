package com.rogiss.project.area42.service.ActionsReactions;

import com.rogiss.project.area42.model.Action;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActionService {

    List<Action> findAll();

    Action findActionById(Long id);

    Action saveAction(Action action);

    void deleteAction(Long id);

}

