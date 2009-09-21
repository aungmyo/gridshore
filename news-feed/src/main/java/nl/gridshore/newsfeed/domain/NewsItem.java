package nl.gridshore.newsfeed.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.Date;

/**
 * @author Jettro Coenradie
 */
@Entity
@Table(name = "NEWS_ITEM")
public class NewsItem extends AbstractEntity {
    private MetaData metaData;
    @Column(name = "title")
    private String title;
    @Column(name = "introduction")
    private String introduction;
    @Column(name = "item")
    private String item;

    public NewsItem() {
        super();
    }

    public NewsItem(String author, Date publicationDate, String title, String introduction, String item) {
        this.metaData = new MetaData(author,publicationDate);
        this.title = title;
        this.introduction = introduction;
        this.item = item;
    }

    public MetaData metaData() {
        return metaData;
    }

    public String title() {
        return title;
    }

    public String introduction() {
        return introduction;
    }

    public String item() {
        return item;
    }

    public void tag(String tag) {
        metaData.tag(tag);
    }
}
