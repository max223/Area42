package com.rogiss.project.area42.model.event.update;

import com.rogiss.project.area42.model.event.object.FacebookChangeObject;

import java.util.List;


public class FacebookEntry {
    private String id;
    private Integer time;
    private String uid;
    private List<FacebookChangeObject> changes;


    public FacebookEntry() {
    }

    public List<FacebookChangeObject> getChanges() {
        return changes;
    }

    public void setChanges(List<FacebookChangeObject> changes) {
        this.changes = changes;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
