package com.rogiss.project.area42.event;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.GetTemporaryLinkResult;
import com.rogiss.project.area42.model.ActionReaction;
import com.rogiss.project.area42.model.UserInfos;
import com.rogiss.project.area42.model.event.object.DropboxEvent;
import com.rogiss.project.area42.model.event.object.FacebookEventObject;
import com.rogiss.project.area42.model.event.object.FacebookPictureObject;
import com.rogiss.project.area42.repository.ActionsReactions.ActionReactionRepository;
import com.rogiss.project.area42.repository.UserRepository;
import com.rogiss.project.area42.service.UserDetailsServiceImpl;
import com.rogiss.project.area42.social.provider.BaseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.List;

@Component
public class DropboxEventListenner {

    @Autowired
    ActionReactionRepository actionReactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BaseProvider baseProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    Environment env;

    @EventListener
    public void handleDropboxEvent(DropboxEvent event) throws DbxException, MalformedURLException {
        UserInfos user = userDetailsService.getUserByDropboxId(event.getAccountId());
        Long userId = user.getId();
        List<ActionReaction> actionReactionList = actionReactionRepository.findByUserId(userId);
        Facebook facebook = new FacebookTemplate(user.getFbaccessToken());

        DbxRequestConfig config = new DbxRequestConfig("dropbox/epitech-Area-42", "fr_FR");
        DbxClientV2 client = new DbxClientV2(config, user.getDbxAccessToken());
        GetTemporaryLinkResult url_photo = client.files().getTemporaryLink(event.getPath());
        UrlResource resource = new UrlResource(url_photo.getLink());
        facebook.mediaOperations().postPhoto(resource);


    }
}