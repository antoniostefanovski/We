package mk.ukim.finki.wp.laboratory1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.laboratory1.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticketOrder")
@AllArgsConstructor
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;

    @PostMapping()
    public String getTicketOrderConfirmedPage(HttpServletRequest request, Model model) {

           String movieTitle = request.getParameter("movie");
           String clientName = request.getParameter("clientName");
           Long numTickets = Long.parseLong(request.getParameter("numTickets"));
           String address = request.getRemoteAddr();

           model.addAttribute("movie", movieTitle);
           model.addAttribute("numberOfTickets", numTickets);
           model.addAttribute("clientName", clientName);
           model.addAttribute("clientAddress", address);

           ticketOrderService.placeOrder(movieTitle, clientName, address, numTickets);

           return "orderConfirmation";
    }

}
