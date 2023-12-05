package mk.ukim.finki.wp.laboratory1.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.laboratory1.model.Production;
import mk.ukim.finki.wp.laboratory1.repository.ProductionRepository;
import mk.ukim.finki.wp.laboratory1.service.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;
    @Override
    public List<Production> findAll() {
        return this.productionRepository.findAll();
    }

    @Override
    public Production findById(long id) {
        return this.productionRepository.findById(id);
    }


}
