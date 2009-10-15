package nl.gridshore.newsfeed.domain;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

/**
 * @author Jettro Coenradie
 */
@Embeddable
public class Author {
    @Basic
    private String userid;

    @Basic
    private String nickName;

    @Basic
    private String email;

    public Author() {
    }

    public Author(String userid, String nickName, String email) {
        this();
        this.userid = userid;
        this.nickName = nickName;
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

}
