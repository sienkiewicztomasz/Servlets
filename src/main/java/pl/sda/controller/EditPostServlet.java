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

@WebServlet(name = "editPostServlet", urlPatterns = "/edit-post")
public class EditPostServlet extends HttpServlet {

    private PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Optional<Post> postToEdit = postService.getPost(id);
        if (postToEdit.isPresent()) {
            req.setAttribute("post", postToEdit.get());
            req.getRequestDispatcher("/edit-post.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", Message.error("Post o id = " + id + " nie istnieje!"));
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String text = req.getParameter("text");
        postService.updatePost(text, id);
        req.setAttribute("message", Message.success("Post został zaktualizowany!"));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}




