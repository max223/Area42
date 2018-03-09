package com.rogiss.project.area42.config;

import com.rogiss.project.area42.model.Action;
import com.rogiss.project.area42.model.Reaction;
import com.rogiss.project.area42.service.ActionsReactions.ActionService;
import com.rogiss.project.area42.service.ActionsReactions.ReactionService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ActionService actionService;

    @Autowired
    ReactionService reactionService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (actionService.findAll().size() == 0)
            loadActions_Reactions();
    }

    private void loadActions_Reactions() {
        Action PostPhotoFb = new Action("Post a photo on Facebook");
        Action CreateFbEvent = new Action("Created an Event on Facebook");
        Action AcceptInvitFb = new Action("Accepted invitation to a Facebook Event");
        Action PostPhotoOnDropbox = new Action("Post photo on dropbox");


        Reaction postEventOnTwitter = new Reaction("Post event on twitter");
        Reaction postPhotoOnDrop = new Reaction("Post photo on dropbox");
        Reaction postPhotoOnFb = new Reaction("Post photo on facebook");
        reactionService.saveReaction(postEventOnTwitter);
        reactionService.saveReaction(postPhotoOnDrop);

        List<Reaction> PostPhotoReaction = new ArrayList<>();
        PostPhotoReaction.add(postPhotoOnDrop);
        PostPhotoReaction.add(postPhotoOnFb);

        List<Reaction> PostEventReaction = new ArrayList<>();
        PostEventReaction.add(postEventOnTwitter);

        PostPhotoOnDropbox.setCompatibleReaction(PostPhotoReaction);
        PostPhotoFb.setCompatibleReaction(PostPhotoReaction);
        CreateFbEvent.setCompatibleReaction(PostEventReaction);
        AcceptInvitFb.setCompatibleReaction(PostEventReaction);

        actionService.saveAction(PostPhotoOnDropbox);
        actionService.saveAction(PostPhotoFb);
        actionService.saveAction(CreateFbEvent);
        actionService.saveAction(AcceptInvitFb);
    }

}
