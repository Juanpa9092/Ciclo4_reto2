package reto2_web.reto2_web.repository;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import reto2_web.reto2_web.interfaces.VegetarianInterface;
import reto2_web.reto2_web.model.Vegetarian;

@Repository
public class VegetarianRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private VegetarianInterface vegetarianCrudRepository;

    public List<Vegetarian> getAll() {
        return vegetarianCrudRepository.findAll();
    }

    public Optional<Vegetarian> getClothe(String reference){
        return vegetarianCrudRepository.findById(reference);
    }
    
    public Vegetarian create(Vegetarian clothe) {
        return vegetarianCrudRepository.save(clothe);
    }

    public void update(Vegetarian clothe) {
        vegetarianCrudRepository.save(clothe);
    }
    
    public void delete(Vegetarian clothe) {
        vegetarianCrudRepository.delete(clothe);
    }

    public List<Vegetarian> vegetarianByPrice(double price){
        Query query = new Query();
        Criteria criterio = Criteria.where("price").is(price);
        query.addCriteria(criterio);

        List<Vegetarian> orders = mongoTemplate.find(query,Vegetarian.class);
        return orders;
    }

    public List<Vegetarian> vegetarianByDescription(String description){
        Query query = new Query();
        Criteria criterio = Criteria.where("description").regex(description);
        query.addCriteria(criterio);

        List<Vegetarian> orders = mongoTemplate.find(query,Vegetarian.class);
        return orders;
    }

}
