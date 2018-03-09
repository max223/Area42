package com.rogiss.project.area42.model.event.object;

import org.omg.CORBA.Object;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class DropboxUpdate {

    private List<String> accounts;
//
//    public DropboxUpdate(List<String> accounts, Object source) {
//        super(source);
//        this.accounts = accounts;
//    }
//
//    public DropboxUpdate(Object source) {
//        super(source);
//    }


    public DropboxUpdate() {
    }

    public DropboxUpdate(List<String> accounts) {
        this.accounts = accounts;
    }

    public List<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }
}
