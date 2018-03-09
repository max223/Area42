package com.rogiss.project.area42.controller;

import com.rogiss.project.area42.model.Action;
import com.rogiss.project.area42.model.ActionReaction;
import com.rogiss.project.area42.model.Reaction;
import com.rogiss.project.area42.service.ActionsReactions.ActionReactionService;
//import com.rogiss.project.area42.service.ActionsReactions.ActionService;
//import com.rogiss.project.area42.service.ActionsReactions.ReactionService;
import com.rogiss.project.area42.service.ActionsReactions.ActionService;
import com.rogiss.project.area42.service.ActionsReactions.ReactionService;
import com.rogiss.project.area42.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    ActionReactionService actionReactionService;

    @Autowired
    ActionService actionService;

    @Autowired
    ReactionService reactionService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = {"/edit/{id}", "/edit"}, method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (id != null)
            model.addAttribute("area", actionReactionService.findOne(id));
        else
            model.addAttribute("area", new ActionReaction());
        model.addAttribute("actionService", actionService);
        model.addAttribute("actions", actionService.findAll());
        model.addAttribute("reactionService", reactionService);
        return "secure/area/editForm";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(Model model, ActionReaction actionReaction) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userDetailsService.getIdByEmail((String) auth.getPrincipal());
        actionReaction.setUserId(userId);
        actionReactionService.saveActionReaction(actionReaction);
        return new ModelAndView("redirect:/area/list");
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userDetailsService.getIdByEmail((String) auth.getPrincipal());
        model.addAttribute("arealist", actionReactionService.findByUserId(userId));
        model.addAttribute("actionService", actionService);
        model.addAttribute("reactionService", reactionService);
        return "secure/area/list";
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView areaDelete(Model model, @PathVariable Long id) {
        actionReactionService.deleteActionReaction(id);
        return new ModelAndView("redirect:/area/list");
    }

    @RequestMapping(value = "/compatible")
    @ResponseBody
    public List<Reaction> getCompatibleReactions(@RequestParam String actionId) {
        Action myAction = actionService.findActionById(Long.valueOf(actionId).longValue());
        return myAction.getCompatibleReaction();
    }

}
