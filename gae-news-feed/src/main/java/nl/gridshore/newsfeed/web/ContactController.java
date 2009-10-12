package nl.gridshore.newsfeed.web;

import nl.gridshore.newsfeed.integration.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jettro Coenradie
 */
@Controller
public class ContactController extends GaeSpringController {
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/contact/form", method = RequestMethod.GET)
    public String presentContactForm(ModelMap modelMap, HttpServletRequest request) {
        try {
        ContactForm contactForm = new ContactForm();

        modelMap.addAttribute("contactForm",contactForm);
        } catch (java.security.AccessControlException e) {
            e.printStackTrace();
        }
        return "contact/form";
    }

    @RequestMapping(value = "/contact/form", method = RequestMethod.POST)
    public String processContactForm(@ModelAttribute("contactForm") ContactForm contactForm, BindingResult result) {
        if (result.hasErrors()) {
            return "contact/form";
        } else {
            mailService.sendMailToAdmin(contactForm.getName(),contactForm.getEmail(),contactForm.getTitle(),contactForm.getMessage());
        }

        return "contact/thankyou";
    }

}
