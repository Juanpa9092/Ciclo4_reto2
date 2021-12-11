package reto2_web.reto2_web.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import reto2_web.reto2_web.interfaces.OrderInterface;
import reto2_web.reto2_web.model.Order;

@Repository
public class OrderRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrderInterface orderCrudRepository;

    public List<Order> getAll(){
        return (List<Order>) orderCrudRepository.findAll();
    }

    public Optional<Order> getOrder(int id){
        return orderCrudRepository.findById(id);
    }

    public List<Order> findByZone(String zona){
        return orderCrudRepository.findByZone(zona);
    }

    public Order create(Order order){
        return orderCrudRepository.save(order);
    }

    public void update(Order order){
        orderCrudRepository.save(order);
    }

    public void delete(Order order){
        orderCrudRepository.delete(order);
    }

    public Optional<Order> lastOrderId(){
        return orderCrudRepository.findTopByOrderByIdDesc();
    }

    public List<Order> orderSalesManById(int id){
        Query query = new Query();
        Criteria criterio = Criteria.where("salesMan.id").is(id);
        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query,Order.class);
        return orders;
    }

    public List<Order> orderSalesManByState(String state, int id){
        Query query = new Query();
        Criteria criterio = Criteria.where("salesMan.id").is(id)
                                .and("status").is(state);
        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query,Order.class);
        return orders;
    }

    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Query query = new Query();
        
        Criteria criterio = Criteria.where("registerDay")
                        .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
                        .lt(LocalDate.parse(dateStr, dtf).plusDays(1).atStartOfDay())
                        .and("salesMan.id").is(id);
        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query,Order.class);
        
        return orders;       
    }
}
