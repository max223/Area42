package com.rogiss.project.area42.model;


//import java.beans.Transient; password encrypt

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "user")
@Table(name = "user")
public class UserInfos implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L; //serialisation ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 3, max = 100, message = "First Name ne peut être plus court que 3 charactères ")
    private String firstName;

    @Size(min = 3, max = 100, message = "Last Name ne peut être plus court que 3 charactères")
    private String lastName;

    @Email(message = "L'email n'est pas au format valide")
    @Size(min = 3, max = 100, message = "L'Email ne peut etre nul")
    private String email;
    private String title;
    private String country;
    private String password;

    @Transient
    private String passwordConfirm;
    private String provider; //just for autologin method
    private String image;
    private String FbaccessToken;
    private String TwitteraccessToken;
    private String LinkedinaccessToken;
    private Long userIdFacebook;
    private Long userIdLinkedin;
    private Long userIdTwitter;
    private String userIdDropbox;
    private String dbxCursor;
    private String dbxAccessToken;



    @ElementCollection
    private List<String> registeredModules = new ArrayList<String>();

    //Getter & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getFbaccessToken() {
        return FbaccessToken;
    }

    public void setFbaccessToken(String fbaccessToken) {
        this.FbaccessToken = fbaccessToken;
    }

    public Long getUserIdFacebook() {
        return userIdFacebook;
    }

    public void setUserIdFacebook(Long userIdFacebook) {
        this.userIdFacebook = userIdFacebook;
    }


    public Long getUserIdLinkedin() {
        return userIdLinkedin;
    }

    public void setUserIdLinkedin(Long userIdLinkedin) {
        this.userIdLinkedin = userIdLinkedin;
    }

    public Long getUserIdTwitter() {
        return userIdTwitter;
    }

    public void setUserIdTwitter(Long userIdTwitter) {
        this.userIdTwitter = userIdTwitter;
    }

    public String getTwitteraccessToken() {
        return TwitteraccessToken;
    }

    public void setTwitteraccessToken(String twitteraccessToken) {
        TwitteraccessToken = twitteraccessToken;
    }

    public String getLinkedinaccessToken() {
        return LinkedinaccessToken;
    }

    public void setLinkedinaccessToken(String linkedinaccessToken) {
        LinkedinaccessToken = linkedinaccessToken;
    }

    //Copy missing infos from anotother UserInfos

    public void populateFromOtherInfo(UserInfos infos) throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            if (f.get(this) == null)
                f.set(this, f.get(infos));
        }
    }

    public void addRegisteredModules(String module) {
        if(!registeredModules.contains(module)) {
            registeredModules.add(module);
        }
    }

// Methods from UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public List getRegisteredModules() {
        return registeredModules;
    }


    public void setUserIdDropbox(String userIdDropbox) {
        this.userIdDropbox = userIdDropbox;
    }

    public String getUserIdDropbox() {
        return userIdDropbox;
    }

    public void setDbxCursor(String dbxCursor) {
        this.dbxCursor = dbxCursor;
    }

    public String getDbxCursor() {
        return dbxCursor;
    }

    public String getDbxAccessToken() {
        return dbxAccessToken;
    }

    public void setDbxAccessToken(String dbxAccessToken) {
        this.dbxAccessToken = dbxAccessToken;
    }

    public void setRegisteredModules(List<String> registeredModules) {
        this.registeredModules = registeredModules;
    }
}
