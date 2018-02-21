package com.rogiss.project.area42.model.event.object;


public class FacebookEventObject implements IFacebookUpdateObject {

    private String event_id;

    public enum  verb {
        accept, create, decline, maybe, update}

    private verb verb;

    public FacebookEventObject(String event_id, FacebookEventObject.verb verb) {
        this.event_id = event_id;
        this.verb = verb;
    }


    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public FacebookEventObject.verb getVerb() {
        return verb;
    }

    public void setVerb(FacebookEventObject.verb verb) {
        this.verb = verb;
    }
}
