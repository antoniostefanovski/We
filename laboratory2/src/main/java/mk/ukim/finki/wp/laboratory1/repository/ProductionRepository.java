package mk.ukim.finki.wp.laboratory1.repository;

import mk.ukim.finki.wp.laboratory1.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductionRepository {

    List<Production> Productions = null;

    public ProductionRepository() {
        Productions = new ArrayList<>();

        Productions.add(new Production("Sony Pictures Entertainment", "USA", "Sony Pic Address"));
        Productions.add(new Production("Paramount Pictures", "USA", "Paramount Pics Address"));
        Productions.add(new Production("20th Century Studios", "USA", "20th Century Address"));
        Productions.add(new Production("Rez Production", "MK", "Rez Address"));
        Productions.add(new Production("Atlantic Pro Media", "MK", "Atlantic Address"));
    }

    public List<Production> findAll() {
        return Productions;
    }

    public Production findById(long id) {
        return Productions.stream()
                .filter(p -> p.getId() == id).findFirst().get();
    }

}
