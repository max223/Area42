package com.rogiss.project.area42.model.event.update;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.util.List;


public class FacebookEntry {
    private String id;
    private Integer time;
    private String uid;
    private List<String> changed_fields;
    //private JSONObject changes;

    public FacebookEntry() {
    }

    public FacebookEntry(String id, Integer time, String uid, List<String> changed_fields) {
        this.id = id;
        this.time = time;
        this.uid = uid;
        this.changed_fields = changed_fields;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<String> getChanged_fields() {
        return changed_fields;
    }

    public void setChanged_fields(List<String> changed_fields) {
        this.changed_fields = changed_fields;
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
/*
    public JSONObject getFacebookUpdate() {
        return changes;
    }

    public void setFacebookUpdate(JSONObject facebookUpdate) {
        this.changes = facebookUpdate;
    }*/
}
