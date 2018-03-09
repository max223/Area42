package com.rogiss.project.area42.model.event.update;


import java.lang.reflect.Array;
import java.util.List;

public class FacebookRequest {

    private String object;
    private List<FacebookEntry> entry;

    public FacebookRequest(String object, List<FacebookEntry> entry) {
        this.object = object;
        this.entry = entry;
    }

    public FacebookRequest() {
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<FacebookEntry> getEntry() {
        return entry;
    }

    public void setEntry(List<FacebookEntry> entry) {
        this.entry = entry;
    }
}
