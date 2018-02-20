package com.rogiss.project.area42.controller;


import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Override spring social flow
@Controller
@RequestMapping("/connect")
public class ChangeDefaultFlowController extends ConnectController {

    public ChangeDefaultFlowController(ConnectionFactoryLocator connectionFactoryLocator,
                                       ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
    }

    @Override
    protected String connectedView(String providerId) {
        return "redirect:/"+providerId;
        //return "redirect:/user";
    }

}
