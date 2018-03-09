package com.rogiss.project.area42.repository;
import com.rogiss.project.area42.model.UserInfos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfos, Integer> {

    UserInfos findByEmail(String email);
    UserInfos findById(Integer id);
    UserInfos findByUserIdTwitter(Long id);
    UserInfos findByUserIdFacebook(Long id);
    UserInfos findByUserIdLinkedin(Long id);
    UserInfos findByUserIdDropbox(String id);


//    Long      returnUserInfosByEmail

}