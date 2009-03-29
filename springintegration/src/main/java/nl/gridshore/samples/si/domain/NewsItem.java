package nl.gridshore.samples.si.domain;

/**
 * Created by IntelliJ IDEA.
 * User: jettrocoenradie
 * Date: Mar 29, 2009
 * Time: 8:39:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class NewsItem {
    private String title;
    private String summary;
    private String body;

    public NewsItem(String title, String summary, String body) {
        this.title = title;
        this.summary = summary;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
