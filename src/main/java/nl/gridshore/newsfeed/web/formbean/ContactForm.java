package nl.gridshore.newsfeed.web.formbean;

/**
 * @author Jettro Coenradie
 */
public class ContactForm {
    private String name;
    private String email;
    private String title;
    private String message;

    public ContactForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
