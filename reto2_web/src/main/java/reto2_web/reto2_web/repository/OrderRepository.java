package reto2_web.reto2_web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import reto2_web.reto2_web.interfaces.OrderInterface;
import reto2_web.reto2_web.model.Order;

@Repository
public class OrderRepository {
    
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
}
