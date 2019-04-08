package pl.sda.filter;

import org.apache.commons.lang3.math.NumberUtils;
import pl.sda.model.Post;
import pl.sda.model.User;
import pl.sda.model.enumeration.Role;
import pl.sda.service.PostService;
import pl.sda.util.Message;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebFilter(filterName = "objectOwner", servletNames = {"deletePost"})
public class ObjectOwnerFilter implements Filter {

    private PostService postService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        postService = PostService.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String id = servletRequest.getParameter("id");


        Optional<Post> optPost = postService.getPost(id);

        if (optPost.isPresent()) {
            Post post = optPost.get();
            User postAuthor = post.getUser();

            HttpServletRequest req = (HttpServletRequest) servletRequest;
            User loggedUser = (User) req.getSession().getAttribute("user");

            if (postAuthor.equals(loggedUser) || (Objects.nonNull(loggedUser) && loggedUser.isAdmin())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                servletRequest.setAttribute("message", Message.error("Nie masz uprawnień"));
            }
        }


        servletRequest.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}