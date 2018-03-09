package com.rogiss.project.area42.social.provider;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import com.rogiss.project.area42.OAuth.DropBoxApi20;
import com.rogiss.project.area42.model.UserInfos;
import com.rogiss.project.area42.repository.UserRepository;
import org.scribe.builder.api.DropBoxApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.net.URI;
import java.net.URISyntaxException;
import com.dropbox.core.*;

@Service
public class DropboxProvider {

    private static final String DROPBOX = "dropbox";

    private static final String REDIRECT_LOGIN = "redirect:/login";

    @Autowired
    BaseProvider baseProvider;

    @Autowired
    UserRepository userRepository;
    public String getDropboxUserData(Model model, UserInfos userForm, String accesstoken) throws IllegalAccessException {


        try {
            populateUserDetailsFromDropbox(userForm, accesstoken);
        } catch (DbxException e) {
            e.printStackTrace();
        }

        UserInfos existing = userRepository.findByEmail(userForm.getEmail());
        if (existing == null) {
            userForm.addRegisteredModules("Dropbox");
            userRepository.save(userForm);
            baseProvider.autoLoginUser(userForm);
            model.addAttribute("loggedInUser",userForm);
        }
        else {
            existing.addRegisteredModules("Dropbox");
            existing.populateFromOtherInfo(userForm);
            userRepository.save(existing);
            baseProvider.autoLoginUser(existing);
            model.addAttribute("loggedInUser",existing);
        }
        return "secure/user";
    }

    private void populateUserDetailsFromDropbox(UserInfos userForm, String accesstoken) throws DbxException {

        DbxRequestConfig config = new DbxRequestConfig("dropbox/epitech-Area-42", "fr_FR");
        DbxClientV2 client = new DbxClientV2(config, accesstoken);
//        client.files().listFolder()
        FullAccount account = client.users().getCurrentAccount();
        userForm.setEmail(account.getEmail());
        userForm.setFirstName(account.getName().getGivenName());
        userForm.setLastName(account.getName().getSurname());
        userForm.setImage(account.getProfilePhotoUrl());
        userForm.setUserIdDropbox(account.getAccountId());
        userForm.setDbxCursor(client.files().listFolder("").getCursor());
        userForm.setDbxAccessToken(accesstoken);
        userForm.setProvider(DROPBOX);
    }
}

