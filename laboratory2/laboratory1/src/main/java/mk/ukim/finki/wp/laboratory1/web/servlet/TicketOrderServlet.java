package mk.ukim.finki.wp.laboratory1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.laboratory1.service.impl.TicketOrderServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/servlet/ticketOrder")
public class TicketOrderServlet extends HttpServlet {

    private final TicketOrderServiceImpl ticketOrderService;
    private final SpringTemplateEngine springTemplateEngine;

    public TicketOrderServlet(TicketOrderServiceImpl ticketOrderService, SpringTemplateEngine springTemplateEngine) {
        this.ticketOrderService = ticketOrderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);

        String movie = req.getParameter("movie");
        Long numberOfTickets = Long.parseLong(req.getParameter("numTickets"));
        String address = req.getRemoteAddr();
        String clientName = req.getParameter("clientName");

        webContext.setVariable("movie", movie);
        webContext.setVariable("numberOfTickets", numberOfTickets);
        webContext.setVariable("clietName", clientName);
        webContext.setVariable("clientAddress", address);

        ticketOrderService.placeOrder(movie, clientName, address, numberOfTickets);

        springTemplateEngine.process("orderConfirmation.html", webContext, resp.getWriter());

    }
}
