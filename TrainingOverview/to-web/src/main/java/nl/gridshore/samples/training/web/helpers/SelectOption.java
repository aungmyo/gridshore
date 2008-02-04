package nl.gridshore.samples.training.web.helpers;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 3, 2008
 * Time: 10:14:29 AM
 * Helper class for ceating select option items
 */
public class SelectOption {
    private String label;
    private String value;

    public SelectOption(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
