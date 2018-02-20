package com.rogiss.project.area42.controller;

import com.rogiss.project.area42.model.event.object.FacebookObject;
import com.rogiss.project.area42.model.event.update.FacebookEventUpdate;
import com.rogiss.project.area42.model.event.update.FacebookPhotoUpdate;
import com.rogiss.project.area42.model.event.update.FacebookUpdate;
import com.rogiss.project.area42.model.event.update.IUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/webhook")
public class WebhookController {


/*
    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> facebook(@RequestParam("hub.mode") final String mode,
                             @RequestParam("hub.verify_token") final String verifyToken,
                             @RequestParam("hub.challenge") final String challenge){

        return ResponseEntity.ok().body(challenge);
    }*/


    @RequestMapping(value = "/facebook", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> facebookUpdate(@RequestBody final String field, @RequestBody final FacebookObject value){

        FacebookUpdate facebookUpdate;

        switch (field) {
            case "events":
                facebookUpdate = new FacebookEventUpdate();
                break;
            case "photos":
                facebookUpdate = new FacebookPhotoUpdate();
                break;
            default:
                return ResponseEntity.unprocessableEntity().body("Not compatible field");
        }

        System.out.println("=========================");
        System.out.println("=========================");
        System.out.println("field =  ");
        System.out.println(field);
        System.out.println("Value = ");
        System.out.println(value);
        System.out.println("=========================");
        System.out.println("=========================");

        facebookUpdate.setField(field);
        facebookUpdate.setValue(value);

        //call callback

        return ResponseEntity.ok().body("Callback in progress");
    }
}
