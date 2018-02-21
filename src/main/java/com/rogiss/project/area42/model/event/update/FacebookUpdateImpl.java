package com.rogiss.project.area42.model.event.update;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogiss.project.area42.model.event.object.FacebookEventObject;
import com.rogiss.project.area42.model.event.object.FacebookPictureObject;
import com.rogiss.project.area42.model.event.object.IFacebookUpdateObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;



public class FacebookUpdateImpl {

    private String field;

    private JSONObject value;

    //protected IFacebookUpdateObject facebookUpdateObject;

    //protected enum verb {add, block, edit, edited, delete, follow, hide, mute, remove, unblock, unhide, update}

    //protected verb verb;


    public FacebookUpdateImpl(String field) {
        this.field = field;
    }

    public FacebookUpdateImpl(String field, JSONObject value) {
        this.field = field;
        this.value = value;
    }


        public FacebookUpdateImpl() {

        }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public JSONObject getValue() {
        return value;
    }

    public void setValue(JSONObject value) throws JSONException {
        this.value = value;
      /*  if (field.equals("events")) {
            String myEventId = value.getString("event_id");
            FacebookEventObject.verb myVerb = FacebookEventObject.verb.valueOf(value.getString("verb"));
            facebookUpdateObject = new FacebookEventObject(myEventId, myVerb);
        }
        else if (field.equals("photos")) {
            String myObjectId = value.getString("object_id");
            FacebookPictureObject.verb myVerb = FacebookPictureObject.verb.valueOf(value.getString("verb"));
            facebookUpdateObject = new FacebookPictureObject(myVerb,myObjectId);
        }*/
    }
/*
    public verb getVerb() {
        return this.verb;
    }

    public void setVerb(verb verb) {
        this.verb = verb;
    }*/
}
