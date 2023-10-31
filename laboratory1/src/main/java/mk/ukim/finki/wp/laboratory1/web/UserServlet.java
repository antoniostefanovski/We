package mk.ukim.finki.wp.laboratory1.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.laboratory1.service.TicketOrderServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/usermovie")
@AllArgsConstructor
public class UserServlet extends HttpServlet {

    private final TicketOrderServiceImpl ticketOrderService;

    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);

        var selectedUserName = req.getParameter("userName");

        var users = ticketOrderService.getTicketOrders();

        var movies = this.ticketOrderService.getClientMovies(selectedUserName);

        webContext.setVariable("clientName", selectedUserName);
        webContext.setVariable("movies", movies);
        webContext.setVariable("selectedUserName", selectedUserName);
        webContext.setVariable("users", users);


        springTemplateEngine.process("userData.html", webContext, resp.getWriter());


    }
}
