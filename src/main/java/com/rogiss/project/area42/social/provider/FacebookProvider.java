package com.rogiss.project.area42.social.provider;
import com.rogiss.project.area42.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    public String getFacebookUserData(Model model, UserInfos userForm) throws IllegalAccessException {

        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return REDIRECT_LOGIN;
        }
        String accessToken = connectionRepository.getPrimaryConnection(Facebook.class).createData().getAccessToken();
        userForm.setFbaccessToken(accessToken);
        populateUserDetailsFromFacebook(userForm);
        //Save the details in DB

        UserInfos existing = userRepository.findByEmail(userForm.getEmail());
        if (existing == null) {
            userForm.addRegisteredModules("Facebook");
            baseProvider.saveUserDetails(userForm);
            baseProvider.autoLoginUser(userForm);
            model.addAttribute("loggedInUser",userForm);
        }
        else {
            existing.populateFromOtherInfo(userForm);
            existing.setUserIdFacebook(userForm.getUserIdFacebook());
            existing.addRegisteredModules("Facebook");
            userRepository.save(existing);
            baseProvider.autoLoginUser(existing);
            model.addAttribute("loggedInUser",existing);
        }
        return "secure/user";
    }

    protected void populateUserDetailsFromFacebook(UserInfos userForm) {
        Facebook facebook = baseProvider.getFacebook();
        String [] fields = { "id", "email",  "first_name", "last_name", "picture", "events",    "photos"};
        User user = facebook.fetchObject("me", User.class, fields);
        facebook.feedOperations().updateStatus("Test sdk");
        userForm.setUserIdFacebook(Long.valueOf(user.getId()).longValue());
        userForm.setEmail(user.getEmail());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setProvider(FACEBOOK);
    }
}
