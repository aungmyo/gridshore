package nl.gridshore.newsfeed.domain;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

/**
 * @author Jettro Coenradie
 */
@Embeddable
public class Author {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Key key;

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

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
