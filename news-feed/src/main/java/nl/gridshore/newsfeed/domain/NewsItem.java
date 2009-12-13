package nl.gridshore.newsfeed.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<Comment>();

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

    public List<Comment> comments() {
        return Collections.unmodifiableList(comments);
    }

    public void addComment(String commenter, String content) {
        comments.add(new Comment(commenter,content));
    }

    /* getters only for working with the taglib */
    public MetaData getMetaData() {
        return metaData;
    }

    public String getTitle() {
        return title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getItem() {
        return item;
    }

    public List<Comment> getComments() {
        return comments();
    }
}
