package pl.sda.service;

import org.apache.commons.lang3.StringUtils;
import pl.sda.model.Post;
import pl.sda.model.User;
import pl.sda.repository.PostRepository;
import pl.sda.util.Message;
import pl.sda.util.ValidationError;

import java.util.List;
import java.util.Optional;

public class PostService {
    private PostRepository postRepository;
    private static PostService instance = null;

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }

        return instance;
    }

    private PostService() {
        this.postRepository = PostRepository.getInstance();
    }

    public List<Post> getPosts() {
        return postRepository.getPosts();
    }


    public void addPost(String text, User user) {
        postRepository.add(text, user);
    }

    public void deletePost(String id) {
        postRepository.delete(id);
    }

    public Optional<ValidationError> validatePost(String text) {
        Optional<ValidationError> error = Optional.empty();

        if (StringUtils.isEmpty(text)) {
            error = Optional.of(new ValidationError("text", "Nie wpisano tekstu"));
        }

        return error;
    }

    public Optional<Post> getPost(String id) {
        return postRepository.getPost(id);
    }
}
