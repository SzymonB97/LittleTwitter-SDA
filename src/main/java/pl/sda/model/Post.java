package pl.sda.model;

import org.bson.Document;
import org.bson.types.ObjectId;
import pl.sda.model.enumeration.Role;
import pl.sda.util.IdGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Post {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");/*zamiana daty na String*/
    private String id;
    private String text;
    private LocalDateTime date;
    private User user;

    public Post(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Document getAsDocument() {
        Document document = new Document("text", text).append("date", date.format(FORMATTER)).append("user", user.getAsDocument());
        if (Objects.nonNull(id)) {
            document.append("_id", new ObjectId(id));/*_id bo tak przyjmuje baza w mongo*/
        }
        return document;
    }

    public static Post getAsPost(Document document) {
        if (Objects.nonNull(document)) {
            String text = document.getString("text");
            LocalDateTime date = LocalDateTime.parse(document.getString("date"), FORMATTER);
            User user = User.getAsUser((Document) document.get("user"));
            ObjectId id = document.getObjectId("_id");

            Post post = new Post(text, user);
            post.setDate(date);

            if (Objects.nonNull(id)) {
                post.setId(id.toString());
            }

            return post;
        } else {
            return null;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(text, post.text) &&
                Objects.equals(date, post.date) &&
                Objects.equals(user, post.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date, user);
    }
}
