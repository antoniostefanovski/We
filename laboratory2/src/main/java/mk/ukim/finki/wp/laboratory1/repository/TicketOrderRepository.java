package mk.ukim.finki.wp.laboratory1.repository;

import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketOrderRepository {

    List<TicketOrder> TicketOrders;

    public TicketOrderRepository() {
        TicketOrders = new ArrayList<>();
    }

    public TicketOrder placeOrder(String movieTitle, String clientName, String address, Long numberOfTickets) {

        var ticketOrder = new TicketOrder(movieTitle, clientName, address, numberOfTickets);

        TicketOrders.add(ticketOrder);

        return ticketOrder;

    }

}
