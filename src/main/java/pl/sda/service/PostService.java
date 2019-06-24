package pl.sda.service;

import org.apache.commons.lang3.StringUtils;
import pl.sda.model.Post;
import pl.sda.model.User;
import pl.sda.repository.PostRepository;
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

    public void addPost(String text, User user) {
        Post postToAdd = new Post(text, user);
        postRepository.save(postToAdd);
    }

    public Optional<Post> deletePost(String id) {
        Optional<Post> optRemovedPost = postRepository.getPost(id);
        if (optRemovedPost.isPresent()) {
            return postRepository.deletePost(id);
        } else {
            return optRemovedPost;
        }
    }

    public boolean updatePost(String newText, String id) {
        Optional<Post> optUpdatedPost = postRepository.getPost(id);
        if (optUpdatedPost.isPresent()) {
            Post updatedPost = optUpdatedPost.get();
            updatedPost.setText(newText);
            return postRepository.update(updatedPost);
        } else {
            return false;
        }
    }

    public List<Post> getPosts() {
        return postRepository.getPosts();
    }

    public Optional<ValidationError> validatePost(String post) {
        Optional<ValidationError> error = Optional.empty();
        if (StringUtils.isEmpty(post)) {
            error = Optional.of(new ValidationError("text", "Dodawany post nie może być pusty!"));
        }
        return error;
    }

    public Optional<Post> getPost(String id) {
        return postRepository.getPost(id);
    }
}
