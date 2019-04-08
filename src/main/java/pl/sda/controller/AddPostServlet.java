package pl.sda.controller;

import pl.sda.model.Post;
import pl.sda.model.User;
import pl.sda.service.PostService;
import pl.sda.util.Message;
import pl.sda.util.ValidationError;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "addPost", value = "/add-post")
public class AddPostServlet extends HttpServlet {

    private PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String text = req.getParameter("text");

        Optional<ValidationError> error = postService.validatePost(text);
        if (!error.isPresent()) {
            postService.addPost(text, user);
            req.setAttribute("message", Message.success("Dodano post"));
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", Message.error(error.get().getErrorMsg()));
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
