package com.rogiss.project.area42.controller;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import com.rogiss.project.area42.model.UserInfos;
import com.rogiss.project.area42.model.event.object.*;
import com.rogiss.project.area42.model.event.update.*;
import com.rogiss.project.area42.repository.UserRepository;
import org.apache.coyote.Response;
import org.hibernate.event.spi.EventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    UserRepository userRepository;

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter crlf = new CommonsRequestLoggingFilter();
        crlf.setIncludeClientInfo(true);
        crlf.setIncludeQueryString(true);
        crlf.setIncludePayload(true);
        return crlf;
    }

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> verifyFacebook(@RequestParam("hub.mode") final String mode,
                                                 @RequestParam("hub.verify_token") final String verifyToken,
                                                 @RequestParam("hub.challenge") final String challenge) {

        return ResponseEntity.ok().body(challenge);
    }


    @RequestMapping(value = "/dropbox", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> verifyDropbox(@RequestParam("challenge") final String challenge) {

        return ResponseEntity.ok().body(challenge);
    }

    @RequestMapping(value = "/facebook", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> facebookUpdate(@RequestBody FacebookRequest request) {


            for (FacebookEntry entry : request.getEntry()) {
            for (FacebookChangeObject change : entry.getChanges()) {
                change.getObject().setUserId(entry.getUid());
                publisher.publishEvent(change.getObject());
            }
        }
        return ResponseEntity.ok().body("Callback in progress");
    }

    @RequestMapping(value = "/dropbox", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> dropboxUpdate(@RequestBody DropboxRequest request) throws DbxException {
        List<DropboxEvent> dropboxEvents = new ArrayList<DropboxEvent>();

        for (String account : request.getList_folder().getAccounts()) {
            UserInfos current = userRepository.findByUserIdDropbox(account);
            DbxRequestConfig config = new DbxRequestConfig("dropbox/epitech-Area-42", "fr_FR");
            DbxClientV2 client = new DbxClientV2(config, current.getDbxAccessToken());
            ListFolderResult results = client.files().listFolderContinue(current.getDbxCursor());
            current.setDbxCursor(results.getCursor());
            userRepository.save(current);
            for (Metadata entry : results.getEntries()) {
                if (!(entry instanceof FileMetadata)) {
                    break;
                } else if (entry.getPathLower().endsWith(".jpg") || entry.getPathLower().endsWith(".JPG") || entry.getPathLower().endsWith(".PNG") || entry.getPathLower().endsWith(".png")) {

                    DropboxEvent event = new DropboxEvent(this);
                    event.setPath(entry.getPathLower());
                    event.setAccountId(request.getList_folder().getAccounts().get(0));
                    dropboxEvents.add(event);
                }
            }
        }

        for (DropboxEvent event : dropboxEvents) {
            publisher.publishEvent(event);
        }
        return ResponseEntity.ok().body("Callback in progress");
    }
}
