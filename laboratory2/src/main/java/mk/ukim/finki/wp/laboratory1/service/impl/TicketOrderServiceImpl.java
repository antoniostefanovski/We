package mk.ukim.finki.wp.laboratory1.service.impl;

import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import mk.ukim.finki.wp.laboratory1.repository.TicketOrderRepository;
import mk.ukim.finki.wp.laboratory1.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    private final TicketOrderRepository ticketOrderRepository;

    public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
    }


    @Override
    public TicketOrder placeOrder(Movie movie, String clientName, String address, Long numberOfTickets) {
        return this.ticketOrderRepository.placeOrder(movie, clientName, address, numberOfTickets);
    }

    @Override
    public List<TicketOrder> getClientMovies(String clientName) {
        return this.ticketOrderRepository.getClientMovies(clientName);
    }

    @Override
    public List<TicketOrder> getTicketOrders() {
        return this.ticketOrderRepository.getTicketOrders();
    }

    @Override
    public TicketOrder findById(long id) {
        return ticketOrderRepository.findById(id);
    }

    @Override
    public void editTicketOrder(Movie movie, String clientName, String address, Long numberOfTickets, Long ticketId) {
        this.ticketOrderRepository.editTicketOrder(movie, clientName, address, numberOfTickets, ticketId);
    }
}
