package com.rogiss.project.area42.model.event.update;

import com.rogiss.project.area42.model.event.object.FacebookObject;

import java.lang.reflect.Field;


public class FacebookPhotoUpdate extends FacebookUpdate {



    public FacebookPhotoUpdate() {
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public void setField(String field) {
        this.field = field;
    }

    @Override
    public FacebookObject getValue() {
        return value;
    }

    @Override
    public void setValue(FacebookObject value) {
        this.value = value;
    }

    @Override
    public FacebookUpdate.verb getVerb() {
        return this.verb;
    }

    @Override
    public void setVerb(FacebookUpdate.verb verb) {
        this.verb = verb;
    }
}
