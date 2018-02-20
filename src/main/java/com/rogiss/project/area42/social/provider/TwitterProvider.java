package com.rogiss.project.area42.social.provider;

import com.rogiss.project.area42.model.UserInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class TwitterProvider {

    private static final String TWITTER = "twitter";

    private static final String REDIRECT_LOGIN = "redirect:/login";

    @Autowired
    BaseProvider baseProvider;

    public String getTwitterUserData(Model model, UserInfos userForm) {

        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return REDIRECT_LOGIN;
        }
        populateUserDetailsFromLinkedIn(userForm);
        //Save the details in DB
        baseProvider.saveUserDetails(userForm);

        //Login the User
        baseProvider.autoLoginUser(userForm);

        model.addAttribute("loggedInUser",userForm);
        return "secure/user";
    }

    private void populateUserDetailsFromLinkedIn(UserInfos userForm) {
        Twitter twitter = baseProvider.getTwitter();
        TwitterProfile twitterUser = twitter.userOperations().getUserProfile();
        userForm.setFirstName(twitterUser.getName());
        userForm.setImage(twitterUser.getProfileImageUrl());
        userForm.setProvider(TWITTER);
    }

}

