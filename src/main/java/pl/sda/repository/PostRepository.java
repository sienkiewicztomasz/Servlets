package pl.sda.repository;

import com.google.common.collect.Lists;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import pl.sda.config.DbUtil;
import pl.sda.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

public class PostRepository {

    private static PostRepository instance = null;
    MongoCollection<Document> posts;

    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }

    private PostRepository() {
        posts = DbUtil.getConnection().getCollection("posts");
    }

    public List<Post> getPosts() {
        FindIterable<Document> documents = posts.find();
        return Lists.newArrayList(documents).stream()
                .map(Post::fromDocument)
                .collect(Collectors.toList());
    }

    public void save(Post post) {
        post.setCreatedDate(LocalDateTime.now());
        posts.insertOne(post.getAsDocument());
    }

    public Optional<Post> getPost(String id) {
        return Optional.ofNullable(Post.fromDocument(posts.find(eq("_id", new ObjectId(id))).first()));
    }

    public boolean update(Post post) {
        return posts.updateOne(eq("_id", new ObjectId(post.getId())), new Document("$set", post.getAsDocument())).isModifiedCountAvailable();
    }

    public Optional<Post> deletePost(String id) {
        return Optional.ofNullable(Post.fromDocument(posts.findOneAndDelete(eq("_id", new ObjectId(id)))));
    }
}
