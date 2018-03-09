package com.rogiss.project.area42.model.event.update;

import com.rogiss.project.area42.model.event.object.DropboxUpdate;

import java.util.List;

public class DropboxRequest {

    private DropboxUpdate list_folder;

    public DropboxRequest() {
    }

    public DropboxRequest(DropboxUpdate list_folder) {
        this.list_folder = list_folder;
    }

    public DropboxUpdate getList_folder() {
        return list_folder;
    }

    public void setList_folder(DropboxUpdate list_folder) {
        this.list_folder = list_folder;
    }
}
