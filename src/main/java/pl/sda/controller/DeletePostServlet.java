package pl.sda.controller;

import pl.sda.model.Post;
import pl.sda.service.PostService;
import pl.sda.util.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "deletePostServlet", urlPatterns = "/delete-post")
public class DeletePostServlet extends HttpServlet {

    private PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Optional<Post> optDeletedPost = postService.deletePost(id);
        if (optDeletedPost.isPresent()) {
            req.setAttribute("message", Message.success("Post został usunięty!"));
        } else {
            req.setAttribute("message", Message.error("Post o id = " + id + " nie istnieje!"));
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}




