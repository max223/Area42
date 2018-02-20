package com.rogiss.project.area42.controller;

import com.rogiss.project.area42.model.UserInfos;
import com.rogiss.project.area42.repository.UserRepository;
import com.rogiss.project.area42.service.Autologin;
import com.rogiss.project.area42.social.provider.FacebookProvider;
import com.rogiss.project.area42.social.provider.LinkedInProvider;
import com.rogiss.project.area42.social.provider.TwitterProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
public class LoginController {
    @Autowired
    FacebookProvider facebookProvider;

    @Autowired
    TwitterProvider twitterProvider;

    @Autowired
    LinkedInProvider linkedInProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Autologin autologin;

    @PostMapping("/registration")
    public String registerUser(HttpServletResponse httpServletResponse, Model model, @Valid UserInfos userInfos, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userInfos.setProvider("REGISTRATION");
        // Save the details in DB
        if (StringUtils.isNotEmpty(userInfos.getPassword())) {
            userInfos.setPassword(bCryptPasswordEncoder.encode(userInfos.getPassword()));
        }
        userRepository.save(userInfos);

        autologin.setSecuritycontext(userInfos);

        model.addAttribute("loggedInUser", userInfos);
        return "secure/user";
    }

    /** If we can't find a user/email combination */
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String loginToFacebook(Model model) {
        return facebookProvider.getFacebookUserData(model, new UserInfos());
    }

    @RequestMapping(value = "/twitter", method = RequestMethod.GET)
    public String loginToGoogle(Model model) {
        return twitterProvider.getTwitterUserData(model, new UserInfos());
    }

    @RequestMapping(value = "/linkedin", method = RequestMethod.GET)
    public String helloFacebook(Model model) {
        return linkedInProvider.getLinkedInUserData(model, new UserInfos());
    }

    @GetMapping("/registration")
    public String showRegistration(UserInfos userInfos) {
        return "registration";
    }
    @RequestMapping(value = { "/","/login" })
    public String login() {
        return "login";
    }
}
