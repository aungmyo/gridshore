package nl.gridshore.newsfeed.domain;

import javax.persistence.*;

/**
 * Root entity object that can be referenced by it's id.
 *
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

    @Lob
    private byte[] thumbnail;

    public Image() {
    }

    public Image(String filename, String contentType, byte[] content, byte[] thumbnail) {
        this.filename = filename;
        this.content = content;
        this.contentType = contentType;
        this.thumbnail = thumbnail;
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

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }
}
