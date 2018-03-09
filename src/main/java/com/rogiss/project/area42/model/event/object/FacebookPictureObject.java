package com.rogiss.project.area42.model.event.object;


public class FacebookPictureObject extends IFacebookValueObject {

    private String userId;



    public enum verb {add, block, edit, edited, delete, follow, hide, mute, remove, unblock, unhide, update;}
    private verb verb;

    private String object_id;



    public FacebookPictureObject(Object source, String userId) {
        super(source);
        this.userId = userId;
    }

    public FacebookPictureObject(Object source) {
        super(source);
    }

    public FacebookPictureObject(Object source, String userId, FacebookPictureObject.verb verb) {
        super(source);
        this.userId = userId;
        this.verb = verb;
    }

    public FacebookPictureObject(Object source, String userId, String object_id) {
        super(source);
        this.userId = userId;
        this.object_id = object_id;
    }

    public FacebookPictureObject.verb getVerb() {
        return verb;
    }

    @Override
    public void setVerb(Enum verb) {
        this.verb = (verb) verb;
    }

    public String getObject_id() {
        return object_id;
    }


    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }


    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

}

