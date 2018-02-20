package com.rogiss.project.area42.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/webhook")
public class WebhookController {



    @RequestMapping(value = "/facebook", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> facebook(@RequestParam("hub.mode") final String mode,
                             @RequestParam("hub.verify_token") final String verifyToken,
                             @RequestParam("hub.challenge") final String challenge){

        return ResponseEntity.ok().body(verifyToken);
    }
}
