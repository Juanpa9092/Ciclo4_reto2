package reto2_web.reto2_web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reto2_web.reto2_web.model.Order;
import reto2_web.reto2_web.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll(){
        return orderRepository.getAll();
    }

    public Optional<Order> getOrder(int id){
        return orderRepository.getOrder(id);
    }

    public List<Order> findByZone(String zona){
        return orderRepository.findByZone(zona);
    }
    
    public Order create(Order order){
        Optional<Order> orderIdMaximo = orderRepository.lastOrderId();

        if (order.getId() == null) {
            if(orderIdMaximo.isEmpty()){
                order.setId(1);
            }else{
                order.setId(orderIdMaximo.get().getId()+1);
            }           
        }

        Optional<Order> e = orderRepository.getOrder(order.getId());
        if(e.isEmpty()){
            return orderRepository.create(order);
        }else{
            return order;
        }
    }

    public Order update(Order order){

        if(order.getId() != null){
            Optional<Order> orderDB = orderRepository.getOrder(order.getId());
            if(!orderDB.isEmpty()){
                if(order.getStatus() != null){
                    orderDB.get().setStatus(order.getStatus());
                }
                orderRepository.update(orderDB.get());
                return orderDB.get();
            }else{
                return order;
            }
        }else{
            return order;
        }
    }

    public boolean delete(int id){
        Boolean aBoolean = getOrder(id).map(order -> {
            orderRepository.delete(order);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public List<Order> orderSalesManById(int id){
        return orderRepository.orderSalesManById(id);
    }

    public List<Order> orderSalesManByState(String state, int id){
        return orderRepository.orderSalesManByState(state, id);
    }

    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        return orderRepository.ordersSalesManByDate(dateStr,id);
    }

}
