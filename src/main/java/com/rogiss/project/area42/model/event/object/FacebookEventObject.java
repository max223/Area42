package com.rogiss.project.area42.model.event.object;


public class FacebookEventObject extends IFacebookValueObject {

    public enum verb {
        accept("accept"), create("create"), decline("decline"), maybe("maybe"), update("update");

        private String name;
        private verb(String s) {
            name = s;
        }
        public boolean equalsName(String otherName) {
            return name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }
    }
    private verb verb;

    private String event_id;

    private String userId;


    public FacebookEventObject(Object source) {
        super(source);

    }


    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getObject_id() {
        return this.getEvent_id();
    }

    @Override
    public void setObject_id(String object_id) {
        this.setEvent_id(object_id);
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

    @Override
    public void setVerb(Enum verb) {
        this.verb = (verb)verb;
    }
}