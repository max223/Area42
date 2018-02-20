package com.rogiss.project.area42.social.provider;

import com.rogiss.project.area42.model.UserInfos;
import com.rogiss.project.area42.repository.UserRepository;
import com.rogiss.project.area42.service.Autologin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.twitter.api.Twitter;

@Configuration
@Scope(value = "request",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseProvider {

    private Facebook facebook;
    private Twitter twitter;
    private LinkedIn linkedIn;
    private ConnectionRepository connectionRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected Autologin autologin;


    public BaseProvider(Facebook facebook, Twitter twitter, LinkedIn linkedIn, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.twitter = twitter;
        this.linkedIn = linkedIn;
        this.connectionRepository = connectionRepository;
    }

    public Facebook getFacebook() {
        return facebook;
    }

    public void setFacebook(Facebook facebook) {
        this.facebook = facebook;
    }

    public ConnectionRepository getConnectionRepository() {
        return connectionRepository;
    }

    public void setConnectionRepository(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setGoogle(Twitter twitter) {
        this.twitter = twitter;
    }

    public LinkedIn getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(LinkedIn linkedIn) {
        this.linkedIn = linkedIn;
    }

    protected void saveUserDetails(UserInfos userBean) {
        if (StringUtils.isNotEmpty(userBean.getPassword())) {
            userBean.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));
        }
        userRepository.save(userBean);

    }

    public void autoLoginUser(UserInfos userBean) {
        autologin.setSecuritycontext(userBean);
    }
}
