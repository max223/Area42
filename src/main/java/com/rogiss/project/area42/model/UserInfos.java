package com.rogiss.project.area42.model;


//import java.beans.Transient; password encrypt
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "user")
@Table(name = "user")
public class UserInfos implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private static final long serialVersionUID = 1L; //serialisation ID

    @NotNull(message = "First Name ne peut etre nul")
    @Size(min = 3, max = 30, message = "First Name ne peut être plus court que 3 charactères ")
    private String firstName;

    @NotNull(message = "Last Name ne peut etre nul")
    @Size(min = 3, max = 30, message = "Last Name ne peut être plus court que 3 charactères")
    private String lastName;

    @NotNull(message = "L'Email ne peut etre nul")
    @Email (message = "L'email n'est pas au format valide")
    @Size(min = 3, max = 30, message = "L'Email ne peut etre nul")
    private  String email;
    private String title;
    private String country;
    private String password;
    private String passwordConfirm;
    private String provider;
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}
