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
    @Column(name="action_id")
    private Long ActionId;
    @Column(name="reaction_id")
    private Long ReactionId;
    @Column (name="user_id")
    private Long userId;

    public ActionReaction() {
    }

    public ActionReaction(Long actionId, Long reactionId, Long userId) {
        ActionId = actionId;
        ReactionId = reactionId;
        this.userId = userId;
    }

    public ActionReaction(Long actionId, Long reactionId) {
        ActionId = actionId;
        ReactionId = reactionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActionId() {
        return ActionId;
    }

    public void setActionId(Long actionId) {
        ActionId = actionId;
    }

    public Long getReactionId() {
        return ReactionId;
    }

    public void setReactionId(Long reactionId) {
        ReactionId = reactionId;
    }

}