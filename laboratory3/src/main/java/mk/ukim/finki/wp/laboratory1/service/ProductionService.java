package mk.ukim.finki.wp.laboratory1.service;

import mk.ukim.finki.wp.laboratory1.model.Production;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductionService {
    List<Production> findAll();

    Production findById(long id);

}
