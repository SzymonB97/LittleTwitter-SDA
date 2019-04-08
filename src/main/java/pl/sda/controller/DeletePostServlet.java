package pl.sda.controller;

import pl.sda.model.User;
import pl.sda.service.PostService;
import pl.sda.util.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "deletePost", value = "/delete-post")
public class DeletePostServlet extends HttpServlet {

    private PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        postService.deletePost(id);

        req.setAttribute("message", Message.success("Usunięto post"));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
