package mk.ukim.finki.wp.laboratory1.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.laboratory1.model.User;
import mk.ukim.finki.wp.laboratory1.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.laboratory1.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.laboratory1.service.AuthenticationServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    private final AuthenticationServiceImpl authService;

    private final SpringTemplateEngine springTemplateEngine;

    public LoginServlet(SpringTemplateEngine springTemplateEngine, AuthenticationServiceImpl authService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authService = authService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context =  new WebContext(webExchange);

        springTemplateEngine.process("login.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context =  new WebContext(webExchange);

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = null;

        try {

            user = authService.login(username,password);

        } catch (InvalidUserCredentialsException | InvalidArgumentsException ex) {

            context.setVariable("hasError",true);
            context.setVariable("error",ex.getMessage());
            springTemplateEngine.process("login.html",context,resp.getWriter());

        }

        if (user != null) {
            req.getSession().setAttribute("user",user);
            resp.sendRedirect("/");
        }

    }
}
