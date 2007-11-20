package nl.gridshore.samples.raffle.web.wicket;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 19, 2007
 * Time: 8:23:10 PM
 * Starting application class for our wicket raffle application
 */
public class RaffleWicketApplication extends WebApplication {

    @Override
    protected void init() {
        // THIS LINE IS IMPORTANT - IT INSTALLS THE COMPONENT INJECTOR THAT WILL
        // INJECT NEWLY CREATED COMPONENTS WITH THEIR SPRING DEPENDENCIES
        addComponentInstantiationListener(new SpringComponentInjector(this));
    }

    public Class getHomePage() {
        return HomePage.class;
    }
}
