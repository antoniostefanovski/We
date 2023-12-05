package mk.ukim.finki.wp.laboratory1.repository;

import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import mk.ukim.finki.wp.laboratory1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {

    TicketOrder findById(long id);

}
