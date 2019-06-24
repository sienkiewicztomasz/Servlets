package pl.sda.filter;

import pl.sda.model.Post;
import pl.sda.model.User;
import pl.sda.service.PostService;
import pl.sda.util.Message;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebFilter(filterName = "objectOwnerFilter", servletNames = {"deletePostServlet"})
public class ObjectOwnerFilter implements Filter {

    private PostService postService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        postService = PostService.getInstance();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String id = req.getParameter("id");
        Optional<Post> optPost = postService.getPost(id);
        if (optPost.isPresent()) {
            Post post = optPost.get();
            User postAuthor = post.getUser();
            User loggedUser = (User) ((HttpServletRequest) req).getSession().getAttribute("user");
            if (postAuthor.equals(loggedUser) || (Objects.nonNull(loggedUser) && loggedUser.isAdmin())) {
                filterChain.doFilter(req, resp);
            } else {
                req.setAttribute("message", Message.error("Nie masz uprawnień!"));
            }
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}
