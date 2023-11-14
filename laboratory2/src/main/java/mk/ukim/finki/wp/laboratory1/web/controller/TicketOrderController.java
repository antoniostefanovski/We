package mk.ukim.finki.wp.laboratory1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import mk.ukim.finki.wp.laboratory1.service.MovieService;
import mk.ukim.finki.wp.laboratory1.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;

    public TicketOrderController(TicketOrderService ticketOrderService, MovieService movieService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
    }

    @PostMapping()
    public String getTicketOrderConfirmedPage(HttpServletRequest request, Model model) {

           var movieId = request.getParameter("movie");
           String clientName = request.getParameter("clientName");
           Long numTickets = Long.parseLong(request.getParameter("numTickets"));
           String address = request.getRemoteAddr();

           var movie = movieService.findById(Long.parseLong(movieId));

           model.addAttribute("movie", movie);
           model.addAttribute("numberOfTickets", numTickets);
           model.addAttribute("clientName", clientName);
           model.addAttribute("clientAddress", address);

           ticketOrderService.placeOrder(movie, clientName, address, numTickets);

           return "orderConfirmation";
    }

    @GetMapping("/userdata")
    public String getOrdersPage(HttpServletRequest request, Model model) {

        var users = ticketOrderService.getTicketOrders();

        model.addAttribute("users", users);

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
                           @RequestParam String clientAddress,
                           @RequestParam Long id) {

        var movie = movieService.findById(movieId);
        ticketOrderService.editTicketOrder(movie, username, clientAddress, tickets, id);

        return "redirect:/ticketOrder/userdata";

    }

}
