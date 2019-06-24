package pl.sda.controller;

import pl.sda.model.User;
import pl.sda.service.PostService;
import pl.sda.util.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addPostServlet", urlPatterns = "/add-post")
public class AddPostServlet extends HttpServlet {

    private PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        User user = (User) req.getSession().getAttribute("user");
        postService.addPost(text, user);
        req.setAttribute("message", Message.success("Post został dodany!"));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}




