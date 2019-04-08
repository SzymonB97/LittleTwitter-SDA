package pl.sda.model;

import org.bson.Document;
import org.bson.types.ObjectId;
import pl.sda.model.enumeration.Role;

import java.util.Objects;

public class User {
    private String id;
    private String login;
    private String password;
    private Role role;

    public User() {

    }

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Document getAsDocument() {
        Document document = new Document("login", login).append("password", password).append("role", role.name());
        if (Objects.nonNull(id)) {
            document.append("_id", new ObjectId(id));/*_id bo tak przyjmuje baza w mongo*/
        }
        return document;
    }

    public static User getAsUser(Document document) {
        if (Objects.nonNull(document)) {
            String login = document.getString("login");
            String password = document.getString("password");
            Role role = Role.valueOf(document.getString("role"));
            ObjectId id = document.getObjectId("_id");

            User user = new User(login, password, role);

            if (Objects.nonNull(id)) {
                user.setId(id.toString());
            }

            return user;
        } else {
            return null;
        }
    }

    public boolean isAdmin() {
        return Role.ADMIN.equals(role);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role);
    }
}
