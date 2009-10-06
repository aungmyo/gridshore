package nl.gridshore.newsfeed.domain;

import javax.persistence.*;

/**
 * @author Jettro Coenradie
 */
@Entity
public class NewsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String author;

    @Basic
    private String title;

    @Basic
    private String introduction;

    @Basic
    private String item;

    public NewsItem() {
        // default constructor is required
    }

    public NewsItem(String author, String title, String introduction, String item) {
        this.author = author;
        this.title = title;
        this.introduction = introduction;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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
}
