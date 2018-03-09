package com.rogiss.project.area42.model.event.object;

import com.rogiss.project.area42.model.event.update.FacebookRequestValueObject;
import org.springframework.context.ApplicationEvent;

public class FacebookChangeObject {

    private String field;

    private FacebookRequestValueObject value;

    private IFacebookValueObject object = null;


    public FacebookChangeObject() {
    }


    public FacebookChangeObject(String field, FacebookRequestValueObject value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;

        if (object != null)
            return;
        switch (field) {
            case "events":
                object = new FacebookEventObject(this);
                break;
            case "photos":
                object = new FacebookPictureObject(this);
                break;
            default:
                object = null;
                break;
        }
    }

    public FacebookRequestValueObject getValue() {
        return value;
    }

    public void setValue(FacebookRequestValueObject value) {
        this.value = value;

        if (object == null) {
            switch (field) {
                case "events":
                    object = new FacebookEventObject(this);
                    break;
                case "photos":
                    object = new FacebookPictureObject(this);
                    break;
                default:
                    return;
            }
        }
        if (field != null && value != null) {
            Enum myVerb = null;
            if (field.equals("events")) {
                object.setObject_id(value.getEvent_id());
                myVerb = FacebookEventObject.verb.valueOf(value.getVerb());

            } else if (field.equals("photos")) {
                object.setObject_id(value.getObject_id());
                myVerb = FacebookPictureObject.verb.valueOf(value.getVerb());
            }
            object.setVerb(myVerb);
        }
    }

    public IFacebookValueObject getObject() {
        return object;
    }

    public void setObject(IFacebookValueObject object) {
        this.object = object;
    }
}
