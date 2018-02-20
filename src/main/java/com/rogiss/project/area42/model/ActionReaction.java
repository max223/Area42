package com.rogiss.project.area42.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "action_reaction")
public class ActionReaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="area_id")
    private Long id;
    @Column(name="action")
    private String Action;
    @Column(name="reaction")
    private String Reaction;
    @Column (name="user_id")
    private Integer userId;

    public ActionReaction() {
    }

    public ActionReaction(String action, String reaction, Integer userId) {
        Action = action;
        Reaction = reaction;
        this.userId = userId;
    }

    public ActionReaction(String action, String reaction) {
        Action = action;
        Reaction = reaction;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getReaction() {
        return Reaction;
    }

    public void setReaction(String reaction) {
        Reaction = reaction;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}