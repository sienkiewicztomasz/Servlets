package pl.sda.model;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Post {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    private String id;
    private String text;
    private User user;
    private LocalDateTime createdDate;

    public Post(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Document getAsDocument() {
        Document doc = new Document("text", text)
                .append("user", user.getAsDocument())
                .append("createdDate", createdDate.format(FORMATTER));
        if (Objects.nonNull(id)) {
            doc.append("_id", new ObjectId(id));
        }
        return doc;
    }

    public static Post fromDocument(Document document) {
        if (Objects.nonNull(document)) {
            ObjectId id = document.getObjectId("_id");
            String text = document.getString("text");
            User user = User.fromDocument((Document) document.get("user"));
            LocalDateTime createdDate = LocalDateTime.parse(document.getString("createdDate"), FORMATTER);

            Post post = new Post(text, user);
            post.setCreatedDate(createdDate);
            if (Objects.nonNull(id)) {
                post.setId(id.toString());
            }
            return post;
        } else {
            return null;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(text, post.text) &&
                Objects.equals(user, post.user) &&
                Objects.equals(createdDate, post.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, user, createdDate);
    }
}
