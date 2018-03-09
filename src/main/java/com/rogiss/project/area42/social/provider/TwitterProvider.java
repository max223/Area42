package com.rogiss.project.area42.social.provider;

import com.rogiss.project.area42.model.UserInfos;
import com.rogiss.project.area42.repository.UserRepository;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;


@Service
public class TwitterProvider {

    private static final String TWITTER = "twitter";

    private static final String REDIRECT_LOGIN = "redirect:/login";

    @Autowired
    BaseProvider baseProvider;

    @Autowired
    UserRepository userRepository;
    public String getTwitterUserData(Model model, UserInfos userForm) throws IllegalAccessException {

        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return REDIRECT_LOGIN;
        }
        populateUserDetailsFromTwitter(userForm);
        userForm.setTwitteraccessToken(connectionRepository.getPrimaryConnection(Twitter.class).createData().getAccessToken());

        UserInfos existing = userRepository.findByEmail(userForm.getEmail());
        if (existing == null) {
            userForm.addRegisteredModules("Twitter");
            userRepository.save(userForm);
            baseProvider.autoLoginUser(userForm);
            model.addAttribute("loggedInUser",userForm);
        }
        else {
            existing.addRegisteredModules("Twitter");
            existing.populateFromOtherInfo(userForm);
            userRepository.save(existing);
            baseProvider.autoLoginUser(existing);
            model.addAttribute("loggedInUser",existing);
        }
        return "secure/user";
    }

    private void populateUserDetailsFromTwitter(UserInfos userForm)  {
        Twitter twitter = baseProvider.getTwitter();
        TwitterProfile twitterUser = twitter.userOperations().getUserProfile();
//        twitter.timelineOperations().updateStatus("wesh test automatisé marche stp la ptn de ta mere jpp du java");
        URI get_mail = null;
        JSONObject tmp_obj = null;
        String email = null;
        try {
            get_mail = new URI("https://api.twitter.com/1.1/account/verify_credentials.json?include_email=true");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            tmp_obj = new JSONObject(twitter.restOperations().getForObject(get_mail, String.class));
            email = (tmp_obj.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        userForm.setEmail(email);
        userForm.setFirstName(twitterUser.getName());
        userForm.setImage(twitterUser.getProfileImageUrl());
        userForm.setUserIdTwitter(twitterUser.getId());
        userForm.setProvider(TWITTER);
    }
}

