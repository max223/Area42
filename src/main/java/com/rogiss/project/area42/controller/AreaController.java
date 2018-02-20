package com.rogiss.project.area42.controller;

import com.rogiss.project.area42.model.ActionReaction;
import com.rogiss.project.area42.service.ActionReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    ActionReactionService actionReactionService;

    @RequestMapping("/show/{id}")
    public String show(Model model, @PathVariable Long id) {
        model.addAttribute("area", actionReactionService.findOne(id));
        return "secure/area/show";
    }

    @RequestMapping(value = {"/edit/{id}","/edit"}, method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if(id != null)
            model.addAttribute("area",actionReactionService.findOne(id));
        else
            model.addAttribute("area", new ActionReaction());
        return "secure/area/editForm";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Model model, ActionReaction actionReaction){
        actionReactionService.saveActionReaction(actionReaction);
        //model.addAttribute("arealist", actionReactionService.findByUserId(userId));
      //  return "secure/area/list";
        return "/secure/dashboard";
    }
    @RequestMapping("/list")
    public String list(Model model, Integer userId){
        model.addAttribute("arealist", actionReactionService.findByUserId(userId));
        return "secure/area/list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String areaDelete(Model model, @PathVariable Long id, Integer userId){
        actionReactionService.deleteActionReaction(id);
        model.addAttribute("arealist", actionReactionService.findByUserId(userId));
        return "secure/area/list";
    }
}
