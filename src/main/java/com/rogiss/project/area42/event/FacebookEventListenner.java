package com.rogiss.project.area42.event;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import com.rogiss.project.area42.model.ActionReaction;
import com.rogiss.project.area42.model.UserInfos;
import com.rogiss.project.area42.model.event.object.DropboxEvent;
import com.rogiss.project.area42.model.event.object.FacebookEventObject;
import com.rogiss.project.area42.model.event.object.FacebookPictureObject;
import com.rogiss.project.area42.model.event.object.IFacebookValueObject;
import com.rogiss.project.area42.repository.ActionsReactions.ActionReactionRepository;
import com.rogiss.project.area42.repository.UserRepository;
import com.rogiss.project.area42.service.UserDetailsServiceImpl;
import com.rogiss.project.area42.social.provider.BaseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Photo;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;
import java.util.List;

@Component
public class FacebookEventListenner {

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

    @EventListener(condition = "#event.getVerb().name() == 'create'")
    public void handleFacebookEventObjectEvent(FacebookEventObject event) {
        UserInfos user = userDetailsService.getUserByFacebookId(Long.valueOf(event.getUserId()).longValue());
        Long userId = user.getId();
        List<ActionReaction> actionReactionList = actionReactionRepository.findByUserId(userId);
        Twitter twitter = new TwitterTemplate(env.getProperty("spring.social.twitter.app-id"), env.getProperty("spring.social.twitter.app-secret"), env.getProperty("twitter.accessToken"), env.getProperty("twitter.accessTokenSecret"));
        FacebookEventObject event1 = (FacebookEventObject) event;
        try {
            for (ActionReaction area : actionReactionList) {
                if (area.getActionId() == 4 && area.getReactionId() == 1)
                    twitter.timelineOperations().updateStatus("Going to this event : facebook.com/events/" + event1.getEvent_id());
            }
        } catch (Exception e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }

    @EventListener(condition = "#event.getVerb().name() == 'accept'")
    public void handleFacebookEventObjectEventAccept(FacebookEventObject event) {
        UserInfos user = userDetailsService.getUserByFacebookId(Long.valueOf(event.getUserId()).longValue());
        Long userId = user.getId();
        List<ActionReaction> actionReactionList = actionReactionRepository.findByUserId(userId);
        Twitter twitter = new TwitterTemplate(env.getProperty("spring.social.twitter.app-id"), env.getProperty("spring.social.twitter.app-secret"), env.getProperty("twitter.accessToken"), env.getProperty("twitter.accessTokenSecret"));
        FacebookEventObject event1 = (FacebookEventObject) event;
        try {
            for (ActionReaction area : actionReactionList) {
                if (area.getActionId() == 4 && area.getReactionId() == 1)
                    twitter.timelineOperations().updateStatus("Going to this event : facebook.com/events/" + event1.getEvent_id());
            }
        } catch (Exception e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }

    @EventListener()
    public void handleFacebookPhotoObject(FacebookPictureObject object) throws IOException, DbxException {
        UserInfos user = userDetailsService.getUserByFacebookId(Long.valueOf(object.getUserId()).longValue());
        Long userId = user.getId();
        List<ActionReaction> actionReactionList = actionReactionRepository.findByUserId(userId);
        Twitter twitter = new TwitterTemplate(env.getProperty("spring.social.twitter.app-id"), env.getProperty("spring.social.twitter.app-secret"), env.getProperty("twitter.accessToken"), env.getProperty("twitter.accessTokenSecret"));
        DbxRequestConfig config = new DbxRequestConfig("dropbox/epitech-Area-42", "fr_FR");
        DbxClientV2 client = new DbxClientV2(config, user.getDbxAccessToken());
        Facebook facebook = new FacebookTemplate(user.getFbaccessToken());
        byte[] nw_photo = facebook.mediaOperations().getPhotoImage(object.getObject_id());
        OutputStream out = new FileOutputStream("tmp_image.jpg");
        out.write(nw_photo);
        out.flush();
        out.close();
        InputStream in = new FileInputStream("tmp_image.jpg");
        FileMetadata metadata = client.files().uploadBuilder("/"+ object.getObject_id()+".jpg")
                .withMode(WriteMode.ADD)
                .uploadAndFinish(in);
//        client.files().upload("/tmp_image.jpg");
        new File("tmp_image.jpg").delete();

    }
}