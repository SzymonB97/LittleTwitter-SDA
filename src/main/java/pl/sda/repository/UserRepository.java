package pl.sda.repository;

import com.google.common.collect.Lists;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import pl.sda.config.DbUtil;
import pl.sda.model.User;
import pl.sda.model.enumeration.Role;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;

public class UserRepository {

    //    private List<User> users;
    private static UserRepository instance = null;
    private MongoCollection<Document> users;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private UserRepository() {
//        users = new ArrayList<>();

        users = DbUtil.getConnection().getCollection("users");

        save(new User("user", "user123", Role.USER));
        save(new User("admin", "admin123", Role.ADMIN));
        save(new User("test", "test123", Role.USER));
    }

    public List<User> getUsers() {
        FindIterable<Document> documents = users.find();

        return Lists.newArrayList(documents).stream()
                /*.map(user -> User.getAsUser(user))*/
                .map(User::getAsUser)
                .collect(Collectors.toList());
    }

    public Optional<User> getUserByLoginData(String login, String password) {
        Document userDoc = users.find(and(eq("login", login), eq("password", password))).first();/*sprawdzamy czy login = login i hasło = hasło*/

        return Optional.ofNullable(User.getAsUser(Objects.requireNonNull(userDoc)));

//        return users.stream()
//                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
//                .findFirst();
    }


    public void save(User user) {
        if (!userExist(user.getLogin())) {
            users.insertOne(user.getAsDocument());
        }
//        user.setId(IdGenerator.next());
//        users.add(user);
    }

    public boolean userExist(String login) {
        return Objects.nonNull(users.find(eq("login", login)).first());

//        return users.stream().anyMatch(user -> user.getLogin().equals(login));
    }
}
