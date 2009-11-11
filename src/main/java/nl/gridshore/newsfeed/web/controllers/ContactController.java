package nl.gridshore.newsfeed.web.controllers;

import nl.gridshore.newsfeed.integration.mail.MailService;
import nl.gridshore.newsfeed.web.formbean.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jettro Coenradie
 */
@Controller
public class ContactController extends GaeSpringController {
    private MailService mailService;

    @Autowired
    public ContactController(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(value = "/contact/form", method = RequestMethod.GET)
    public String presentContactForm(ModelMap modelMap) {
        ContactForm contactForm = new ContactForm();
        modelMap.addAttribute("contactForm", contactForm);
        return "contact/form";
    }

    @RequestMapping(value = "/contact/form", method = RequestMethod.POST)
    public String processContactForm(@ModelAttribute("contactForm") ContactForm contactForm,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return "contact/form";
        } else {
            mailService.sendMailToAdmin(contactForm.getName(),
                                        contactForm.getEmail(),
                                        contactForm.getTitle(),
                                        contactForm.getMessage());
        }

        return "contact/thankyou";
    }

}
