package nl.gridshore.samples.raffle.web.gwt.client;

import com.google.gwt.user.client.ui.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 18-okt-2007
 * Time: 23:17:00
 * This pop-up is used to present the winner.
 */
public class WinnerPopup extends PopupPanel {
    public WinnerPopup(String message) {
        super(true);
        VerticalPanel panel = new VerticalPanel();
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.add(new Image("winner.jpg"));
        Label label = new Label(message);
        label.setStyleName("popup_message");
        panel.add(label);
        setWidget(panel);
    }
}
