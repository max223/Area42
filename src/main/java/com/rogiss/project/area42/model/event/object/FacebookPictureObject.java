package com.rogiss.project.area42.model.event.object;


public class FacebookPictureObject implements IFacebookUpdateObject {

    public enum verb {add, block, edit, edited, delete, follow, hide, mute, remove, unblock, unhide, update}

    private verb verb;

    private String object_id;

    public FacebookPictureObject(FacebookPictureObject.verb verb, String object_id) {
        this.verb = verb;
        this.object_id = object_id;
    }

    public FacebookPictureObject.verb getVerb() {
        return verb;
    }

    public void setVerb(FacebookPictureObject.verb verb) {
        this.verb = verb;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }
}

