package mk.ukim.finki.wp.laboratory1.service.impl;

import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import mk.ukim.finki.wp.laboratory1.repository.TicketOrderRepository;
import mk.ukim.finki.wp.laboratory1.service.TicketOrderService;
import org.springframework.stereotype.Service;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    private final TicketOrderRepository ticketOrderRepository;

    public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
    }


    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, Long numberOfTickets) {
        return this.ticketOrderRepository.placeOrder(movieTitle, clientName, address, numberOfTickets);
    }
}
