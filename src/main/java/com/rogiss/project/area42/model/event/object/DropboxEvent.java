package com.rogiss.project.area42.model.event.object;

import org.springframework.context.ApplicationEvent;

public class DropboxEvent extends ApplicationEvent {

    private String path;
    private String accountId;

    public DropboxEvent(Object source) {
        super(source);
    }

    public DropboxEvent(Object source, String path) {
        super(source);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
