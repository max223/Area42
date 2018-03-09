package com.rogiss.project.area42.model.event.update;

public class FacebookRequestValueObject {

    private String verb;
    private String event_id;
    private String object_id;

    public FacebookRequestValueObject() {
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }
}
