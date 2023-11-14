package mk.ukim.finki.wp.laboratory1.service;

import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import org.springframework.stereotype.Service;

@Service
public interface TicketOrderService {

    TicketOrder placeOrder(String movieTitle, String clientName, String address, Long numberOfTickets);

}
