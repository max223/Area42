package com.rogiss.project.area42.model.event.update;

import com.rogiss.project.area42.model.event.object.IFacebookUpdateObject;

public abstract class FacebookUpdate implements IUpdate {

     protected String field;

     protected IFacebookUpdateObject value;

    protected enum verb {add, block, edit, edited, delete, follow, hide, mute, remove, unblock, unhide, update}

    protected verb verb;


    public abstract String  getField();

    public abstract void setField(String field);

    public abstract IFacebookUpdateObject getValue();

    public abstract void setValue(IFacebookUpdateObject value);

    public abstract FacebookUpdate.verb getVerb();

    public abstract void setVerb(FacebookUpdate.verb verb);
}
