package com.rogiss.project.area42.social.provider;

import com.rogiss.project.area42.model.UserInfos;
import com.rogiss.project.area42.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class LinkedInProvider {

    private static final String LINKED_IN = "linkedIn";

    private static final String REDIRECT_LOGIN = "redirect:/login";


    @Autowired
    BaseProvider baseProvider;

    @Autowired
    UserRepository userRepository;

    public String getLinkedInUserData(Model model, UserInfos userForm) throws IllegalAccessException {

        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return REDIRECT_LOGIN;
        }
        populateUserDetailsFromLinkedIn(userForm);

        UserInfos existing = userRepository.findByEmail(userForm.getEmail());
        if (existing == null) {
            userForm.addRegisteredModules("LinkedIn");
            baseProvider.saveUserDetails(userForm);
            baseProvider.autoLoginUser(userForm);
        }
        else {
            existing.addRegisteredModules("LinkedIn");
            existing.populateFromOtherInfo(userForm);
            existing.setUserIdLinkedin(userForm.getUserIdLinkedin());
            baseProvider.saveUserDetails(existing);
            baseProvider.autoLoginUser(existing);
        }
        model.addAttribute("loggedInUser", userForm);
        return "secure/user";
    }

    private void populateUserDetailsFromLinkedIn(UserInfos userForm) {
        LinkedIn linkedIn = baseProvider.getLinkedIn();
        LinkedInProfileFull linkedInUser = linkedIn.profileOperations().getUserProfileFull();
        linkedIn.networkUpdateOperations().getCurrentShare();
        userForm.setEmail(linkedInUser.getEmailAddress());
        userForm.setFirstName(linkedInUser.getFirstName());
        userForm.setLastName(linkedInUser.getLastName());
        userForm.setImage(linkedInUser.getProfilePictureUrl());
        userForm.setUserIdLinkedin(Long.valueOf(linkedInUser.getId()).longValue());
        userForm.setProvider(LINKED_IN);
    }

}

