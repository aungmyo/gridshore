package nl.gridshore.newsfeed.domain;

import javax.persistence.*;

/**
 * @author Jettro Coenradie
 */
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String filename;

    @Basic
    private String contentType;

    @Lob
    private byte[] content;

    public Image() {
    }

    public Image(String filename, String contentType, byte[] content) {
        this.filename = filename;
        this.content = content;
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public byte[] getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
