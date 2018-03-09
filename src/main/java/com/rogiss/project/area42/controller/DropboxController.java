package com.rogiss.project.area42.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.rogiss.project.area42.model.UserInfos;
import com.rogiss.project.area42.social.provider.DropboxProvider;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.rogiss.project.area42.OAuth.OAuthServiceProvider;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

@Controller
@RequestMapping("/connect/dropbox")
public class DropboxController {

    @Autowired
    private DropboxProvider dropboxProvider;

    @Autowired
    @Qualifier("dropboxServiceProvider")
    private OAuthServiceProvider dropboxServiceProvider;

    private static final Token EMPTY_TOKEN = null;

    @RequestMapping(value = "/login-dropbox", method = RequestMethod.GET)
    public String loginToDropBox(Map<String, Object> map, WebRequest request) {
        OAuthService service = dropboxServiceProvider.getService();
        String authUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
        System.out.println("RequestToken: " + authUrl);
        return "redirect:" + authUrl;
    }

    @RequestMapping(value = { "/callback" }, method = RequestMethod.GET)
    public String callback(
            @RequestParam(value = "oauth_token", required = false) String oauthToken,
            @RequestParam(value = "code", required = false) String oauthVerifier,
            WebRequest request, Map<String, Object> map, Model model) throws IllegalAccessException {

        OAuthService service = dropboxServiceProvider.getService();

        // getting access token
        Verifier verifier = new Verifier(oauthVerifier);
        Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);

        // store access token as a session attribute
        request.setAttribute("oauthAccessToken", accessToken, SCOPE_SESSION);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false); // create a
        // new
        // session
        session.setAttribute("accessToken", accessToken);

        return dropboxProvider.getDropboxUserData(model, new UserInfos(), accessToken.getToken());
    }
}