package com.rogiss.project.area42.social.provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.rogiss.project.area42.model.UserInfos;


@Service
public class FacebookProvider  {

    private static final String FACEBOOK = "facebook";
    private static final String REDIRECT_LOGIN = "redirect:/login";

    @Autowired
    BaseProvider baseProvider ;


    public String getFacebookUserData(Model model, UserInfos userForm) {

        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return REDIRECT_LOGIN;
        }
        populateUserDetailsFromFacebook(userForm);
        //Save the details in DB
        baseProvider.saveUserDetails(userForm);

        //Login the User
        baseProvider.autoLoginUser(userForm);

        model.addAttribute("loggedInUser",userForm);
        return "secure/user";
    }

    protected void populateUserDetailsFromFacebook(UserInfos userForm) {
        Facebook facebook = baseProvider.getFacebook();
        String [] fields = { "id", "email",  "first_name", "last_name", "picture"};
        User user = facebook.fetchObject("me", User.class, fields);
        userForm.setEmail(user.getEmail());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setProvider(FACEBOOK);
    }



}
