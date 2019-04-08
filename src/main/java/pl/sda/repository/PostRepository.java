package pl.sda.repository;

import com.google.common.collect.Lists;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import pl.sda.config.DbUtil;
import pl.sda.model.Post;
import pl.sda.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

public class PostRepository {

    //    private List<Post> posts;
    private static PostRepository instance = null;
    private MongoCollection<Document> posts;

    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }

    private PostRepository() {
//        posts = new ArrayList<>();

        posts = DbUtil.getConnection().getCollection("posts");
    }

    public List<Post> getPosts() {
        FindIterable<Document> documents = posts.find();

        return Lists.newArrayList(documents).stream().map(Post::getAsPost).collect(Collectors.toList());
    }

    public void add(String text, User user) {
        Post post = new Post(text, user);
        post.setDate(LocalDateTime.now());
        posts.insertOne(post.getAsDocument());

//        Post post = new Post(text, user);
//        post.setId(IdGenerator.next());
//        posts.add(post);
    }

    public void delete(String id) {
        Post.getAsPost(posts.findOneAndDelete((eq("_id", new ObjectId(id)))));

//        for (int i = 0; i < posts.size(); i++) {
//            if (posts.get(i).getId().equals(id)) {
//                posts.remove(posts.get(i));
//            }
//        }
    }

    public Optional<Post> getPost(String id) {
        Document postDoc = posts.find(eq("_id", id)).first();

        return Optional.ofNullable(Post.getAsPost(Objects.requireNonNull(postDoc)));
//        return posts.stream().filter(post -> post.getId().equals(id)).findFirst();
    }
}
