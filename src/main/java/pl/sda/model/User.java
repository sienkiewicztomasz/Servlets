package pl.sda.model;

import org.bson.Document;
import org.bson.types.ObjectId;
import pl.sda.model.enimeration.Role;

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
        Document doc = new Document("login", login)
                            .append("password", password)
                            .append("role", role.name());
        if (Objects.nonNull(id)) {
            doc.append("_id", new ObjectId(id));
        }
        return doc;
    }

    public static User fromDocument(Document document) {
        if (Objects.nonNull(document)) {
            ObjectId id = document.getObjectId("_id");
            String login = document.getString("login");
            String password = document.getString("password");
            Role role = Role.valueOf(document.getString("role"));

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
