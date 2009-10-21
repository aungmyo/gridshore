package nl.gridshore.newsfeed.web.controllers;

import nl.gridshore.newsfeed.service.ReceivedMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jettro Coenradie
 */
@Controller
public class ReceivedMessageController {
    @Autowired
    private ReceivedMessageService receivedMessageService;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String showMessages(ModelMap modelMap) {
        modelMap.addAttribute("receivedMessages",receivedMessageService.listAllReceivedMessages());
        return "receivedmessages";
    }
}
