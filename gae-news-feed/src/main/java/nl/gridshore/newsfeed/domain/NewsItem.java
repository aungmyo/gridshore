package nl.gridshore.newsfeed.domain;

import javax.persistence.*;

/**
 * Root entity object
 *
 * @author Jettro Coenradie
 */
@Entity
public class NewsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Author author;

    @Basic
    private Long imageId;

    @Basic
    private String title;

    @Basic
    private String introduction;

    @Basic
    private String item;

    public NewsItem() {
        // default constructor is required
    }

    public NewsItem(Author author, String title, String introduction, String item) {
        this();
        this.author = author;
        this.title = title;
        this.introduction = introduction;
        this.item = item;
    }

    public NewsItem(Author author, String title, String introduction, String item, Long imageId) {
        this();
        this.author = author;
        this.title = title;
        this.introduction = introduction;
        this.item = item;
        this.imageId = imageId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
