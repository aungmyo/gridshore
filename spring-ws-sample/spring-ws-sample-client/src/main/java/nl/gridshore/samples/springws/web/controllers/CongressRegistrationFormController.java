package nl.gridshore.samples.springws.web.controllers;

import javax.servlet.http.HttpServletRequest;

import nl.gridshore.samples.springws.domain.Registrant;
import nl.gridshore.samples.springws.services.CongressManager;
import nl.gridshore.samples.springws.web.command.CongressRegistrationCommand;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Jettro.Coenradie
 * 
 * Form controller handling a form submission for registering for a congress
 */
public class CongressRegistrationFormController extends SimpleFormController {
	private CongressManager congressManager;
	
	/**
	 * Default constructor
	 * @param congressManager CongressManager is used to handle the entered data
	 */
	public CongressRegistrationFormController(CongressManager congressManager) {
		this.congressManager = congressManager;
	}
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		CongressRegistrationCommand congressRegistration = (CongressRegistrationCommand) command;
		congressManager.createCongressRegistration(congressRegistration.getCongressId(),
				congressRegistration.getRegistrant(), congressRegistration.getSessionIds());
		return new ModelAndView(getSuccessView());
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		CongressRegistrationCommand command = (CongressRegistrationCommand) super.formBackingObject(request);
		if (command.getRegistrant() == null) {
			command.setRegistrant(new Registrant());
		}
		return command;
	}

}
