package com.rogiss.project.area42.model.event.update;

import com.rogiss.project.area42.model.event.object.IFacebookUpdateObject;

public class FacebookEventUpdate extends  FacebookUpdate {


    public FacebookEventUpdate() {
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
    public IFacebookUpdateObject getValue() {
        return value;
    }

    @Override
    public void setValue(IFacebookUpdateObject value) {
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
