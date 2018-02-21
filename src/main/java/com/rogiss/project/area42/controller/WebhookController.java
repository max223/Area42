package com.rogiss.project.area42.controller;

import com.rogiss.project.area42.model.event.update.*;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@RestController
@RequestMapping("/webhook")
public class WebhookController {


    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter crlf = new CommonsRequestLoggingFilter();
        crlf.setIncludeClientInfo(true);
        crlf.setIncludeQueryString(true);
        crlf.setIncludePayload(true);
        return crlf;
    }
    /*private final Logger log = LoggerFactory.getLogger(this.getClass());

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
    public ResponseEntity<String> facebookUpdate(@RequestBody FacebookRequest response){



        System.out.println("=========================");
        System.out.println("=========================");
  //      System.out.println("field =  ");
        response.toString();
        //        System.out.println(response.getField());/**/
//        System.out.println("Value = ");
//        System.out.println(response.getValue());
        System.out.println("=========================");
        System.out.println("=========================");
        FacebookUpdate facebookUpdate;
    /*

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

            log.debug("=========================");
            log.debug("=========================");
            log.debug("field =  ");
    //        log.debug(field);
            log.debug("Value = ");
    //        log.debug(String.valueOf(value));
            log.debug("=========================");
            log.debug("=========================");
    */

//        facebookUpdate.setField(field);
//        facebookUpdate.setValue(value);

        //call callback

        return ResponseEntity.ok().body("Callback in progress");
    }
}
