package mk.ukim.finki.wp.laboratory1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import mk.ukim.finki.wp.laboratory1.model.User;
import mk.ukim.finki.wp.laboratory1.service.MovieService;
import mk.ukim.finki.wp.laboratory1.service.TicketOrderService;
import mk.ukim.finki.wp.laboratory1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;
    private final UserService userService;

    public TicketOrderController(TicketOrderService ticketOrderService, MovieService movieService, UserService userService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
        this.userService = userService;
    }

    @PostMapping()
    public String getTicketOrderConfirmedPage(HttpServletRequest request, Model model) {

           var movieId = request.getParameter("movie");
           String clientName = request.getParameter("clientName");
           Long numTickets = Long.parseLong(request.getParameter("numTickets"));
           String address = request.getRemoteAddr();
           User user = userService.findUserByUsername(clientName);

           var movie = movieService.findById(Long.parseLong(movieId));

           model.addAttribute("movie", movie);
           model.addAttribute("numberOfTickets", numTickets);
           model.addAttribute("clientName", clientName);
           model.addAttribute("clientAddress", address);

           ticketOrderService.placeOrder(movie, user, numTickets);

           return "orderConfirmation";
    }

    @GetMapping("/userdata")
    public String getOrdersPage(HttpServletRequest request, Model model) {

        var orders = ticketOrderService.getTicketOrders();

        model.addAttribute("orders", orders);

        return "userdata";

    }

    @GetMapping("/edit-user/{ticketId}")
    public String getEditUserPage(@PathVariable Long ticketId, Model model) {

        var ticketOrder = ticketOrderService.findById(ticketId);
        var movies = movieService.listAll();

        model.addAttribute("ticketOrder", ticketOrder);
        model.addAttribute("movies", movies);

        return "edit-user";

    }

    @PostMapping("/edit")
    public String editUser(@RequestParam String username,
                           @RequestParam Long movieId,
                           @RequestParam Long tickets,
                           @RequestParam Long id) {

        var movie = movieService.findById(movieId);
        var user = userService.findUserByUsername(username);
        ticketOrderService.editTicketOrder(movie, user, tickets, id);

        return "redirect:/ticketOrder/userdata";

    }

    @PostMapping("buyTickets")
    public String buyTicketsFromCart(@RequestParam String username, Model model){
        List<TicketOrder> tickets = ticketOrderService.placeOrderFromShoppingCart(username);

        model.addAttribute("tickets", tickets);

        return "ticket-orders";
    }

}
