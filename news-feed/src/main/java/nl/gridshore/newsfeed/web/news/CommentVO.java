package nl.gridshore.newsfeed.web.news;

/**
 * @author Jettro Coenradie
 */
public class CommentVO {
    private Long newsItemId;
    private Long commentId;

    private String commenter;
    private String content;

    public Long getNewsItemId() {
        return newsItemId;
    }

    public void setNewsItemId(Long newsItemId) {
        this.newsItemId = newsItemId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
