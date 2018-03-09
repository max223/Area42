package com.rogiss.project.area42.model.event.object;

import org.springframework.context.ApplicationEvent;

public abstract class IFacebookValueObject extends ApplicationEvent {

    protected String userId;

    public IFacebookValueObject(Object source, String userId) {
        super(source);
        this.userId = userId;
    }

    public IFacebookValueObject(Object source) {
            super(source);
    }


    public abstract String getObject_id();
    public abstract void setObject_id(String object_id);
     public abstract java.lang.Enum getVerb();
    public abstract void setVerb(java.lang.Enum verb);
    public abstract String getUserId();
    public abstract void setUserId(String userId);
}