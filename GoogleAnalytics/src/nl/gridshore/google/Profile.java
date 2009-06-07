package nl.gridshore.google;

/**
 * Simple container for a profile
 *
 * @author Jettro Coenradie
 */
public class Profile {
    private String title;
    private String uniqueId;

    public Profile(String title, String uniqueId) {
        if (title == null || "".equals(title)) {
            throw new IllegalArgumentException("title should not be empty");
        }
        if (uniqueId == null || "".equals(uniqueId)) {
            throw new IllegalArgumentException("uniqueId should not be empty");
        }
        this.title = title;
        this.uniqueId = uniqueId;
    }

    public String getTitle() {
        return title;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}
