package com.rogiss.project.area42.social.provider;

import com.rogiss.project.area42.model.UserInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class LinkedInProvider  {

    private static final String LINKED_IN = "linkedIn";

    private static final String REDIRECT_LOGIN = "redirect:/login";


    @Autowired
    BaseProvider baseProvider;

    public String getLinkedInUserData(Model model, UserInfos userForm) {

        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
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
        LinkedIn linkedIn = baseProvider.getLinkedIn();
        LinkedInProfileFull linkedInUser = linkedIn.profileOperations().getUserProfileFull();
        userForm.setEmail(linkedInUser.getEmailAddress());
        userForm.setFirstName(linkedInUser.getFirstName());
        userForm.setLastName(linkedInUser.getLastName());
        userForm.setImage(linkedInUser.getProfilePictureUrl());
        userForm.setProvider(LINKED_IN);
    }

}
