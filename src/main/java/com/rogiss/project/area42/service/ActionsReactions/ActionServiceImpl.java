package com.rogiss.project.area42.service.ActionsReactions;

import com.rogiss.project.area42.model.Action;
import com.rogiss.project.area42.repository.ActionsReactions.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    ActionRepository actionRepository;

    @Override
    public List<Action> findAll() {
        return actionRepository.findAll();
    }

    @Override
    public Action findActionById(Long id) {
        return actionRepository.findActionById(id);
    }

    @Override
    public Action saveAction(Action action) {
        actionRepository.save(action);
        return action;
    }

    @Override
    public void deleteAction(Long id) {
        actionRepository.delete(id);
    }

    @Override
    public Action updateAction(Action old_action) {

        Action toUpdate = actionRepository.findActionById(old_action.getId());
        toUpdate.setCompatibleReaction(old_action.getCompatibleReaction());
        toUpdate.setDescription(old_action.getDescription());
        toUpdate.setId(old_action.getId());
        actionRepository.save(toUpdate);
        return toUpdate;
    }

}
