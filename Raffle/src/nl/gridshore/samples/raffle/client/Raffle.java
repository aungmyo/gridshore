package nl.gridshore.samples.raffle.client;

import com.bouwkamp.gwt.user.client.ui.RoundedPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import org.gwtwidgets.client.ui.LightBox;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 10-okt-2007
 * Time: 22:51:41
 * EntryPoint class for the raffle application
 */
public class Raffle implements EntryPoint {
    
    public void onModuleLoad() {
        final ScrollPanel contentPanel = new ScrollPanel();
        contentPanel.setStyleName("content_panel");
        RoundedPanel roundedContentPanel = new RoundedPanel(contentPanel,RoundedPanel.ALL,5);
        roundedContentPanel.setStyleName("rounded_content_panel");
        MenuBar menuBar = createMenuBar(contentPanel);
        RoundedPanel roundedMenuBar = new RoundedPanel(menuBar, RoundedPanel.ALL,5);
        RootPanel.get().add(roundedMenuBar);
        RootPanel.get().add(roundedContentPanel);
    }

    private MenuBar createMenuBar(final ScrollPanel contentPanel) {
        MenuBar menuBar = new MenuBar();
        menuBar.addItem("New Name", new Command() {
            public void execute() {
                contentPanel.setWidget(createNewNameInput(contentPanel));
            }
        });
        menuBar.addItem("All names", new Command() {
            public void execute() {
                contentPanel.setWidget(createListNames());
            }
        });
        menuBar.addItem("Raffle", new Command() {
            public void execute() {
                contentPanel.setWidget(createButtonForRandomName(contentPanel));
            }
        });
        menuBar.addItem("Winners", new Command() {
            public void execute() {
                contentPanel.setWidget(createWinners());
            }
        });
        return menuBar;
    }

    private FlowPanel createButtonForRandomName(final ScrollPanel contentPanel) {
        final FlowPanel randomNameButtonPanel = new FlowPanel();
        final TextBox priceDescTextBox = new TextBox();
        randomNameButtonPanel.add(new Label("Name of price : "));
        randomNameButtonPanel.add(priceDescTextBox);
        randomNameButtonPanel.add(new Button("Random Name", new ClickListener() {
            public void onClick(Widget sender) {
                FlowPanel nameAndButton = createButtonForRandomName(contentPanel);
                nameAndButton.add(createRandomNamePanel(priceDescTextBox.getText()));
                contentPanel.setWidget(nameAndButton);
            }
        }));
        return randomNameButtonPanel;
    }

    private FlowPanel createRandomNamePanel(String priceDesc) {
        final FlowPanel randomName = new FlowPanel();
        AsyncCallback callback = new AsyncCallback() {
            public void onFailure(Throwable caught) {
                GWT.log("Problem while getting random name",caught);
            }

            public void onSuccess(Object result) {
                randomName.add(new Label((String) result));
                LightBox lightBox = new LightBox(new WinnerPopup((String)result));
                lightBox.show();
            }
        };
        RaffleServiceGwtRemoteAsync service = RaffleServiceGwtRemote.App.getInstance();
        service.getRandomName(priceDesc,callback);
        return randomName;
    }

    private ScrollPanel createListNames() {
        final ScrollPanel listNames = new ScrollPanel();
        AsyncCallback callback = new AsyncCallback() {
            public void onFailure(Throwable caught) {
                GWT.log("Problem while getting random name",caught);
            }

            public void onSuccess(Object result) {
                ArrayList names = (ArrayList) result;
                String resultString = "";
                for(Iterator iter = names.iterator();iter.hasNext();) {
                    resultString += (String) iter.next() + "\n";
                }
                listNames.add(new Label(resultString));
            }
        };
        RaffleServiceGwtRemoteAsync service = RaffleServiceGwtRemote.App.getInstance();
        service.getAllNames(callback);
        return listNames;
    }

    private Panel createNewNameInput(final ScrollPanel contentPanel) {
        VerticalPanel form = new VerticalPanel();
        HorizontalPanel nameForm = new HorizontalPanel();
        nameForm.add(new Label("Name"));
        final TextBox nameTextBox = new TextBox();
        nameTextBox.setName("personName");
        nameForm.add(nameTextBox);
        form.add(nameForm);
        Button saveButton = new Button("Store",new ClickListener(){
            public void onClick(Widget sender) {
                String name = nameTextBox.getText();
                RaffleServiceGwtRemoteAsync service = RaffleServiceGwtRemote.App.getInstance();
                AsyncCallback callback = new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        GWT.log("Failure while creating a new name",caught);
                    }

                    public void onSuccess(Object result) {
                        contentPanel.setWidget(new Label("Name is stored"));
                    }
                };
                service.storeName(name,callback);
            }
        });
        form.add(saveButton);
        return form;
    }

    private ScrollPanel createWinners() {
        final ScrollPanel listNames = new ScrollPanel();
        AsyncCallback callback = new AsyncCallback() {
            public void onFailure(Throwable caught) {
                GWT.log("Problem while getting random name",caught);
            }

            public void onSuccess(Object result) {
                ArrayList names = (ArrayList) result;
                String resultString = "";
                for(Iterator iter = names.iterator();iter.hasNext();) {
                    resultString += (String) iter.next() + "\n";
                }
                listNames.add(new Label(resultString));
            }
        };
        RaffleServiceGwtRemoteAsync service = RaffleServiceGwtRemote.App.getInstance();
        service.getAllWinners(callback);
        return listNames;
    }
}
