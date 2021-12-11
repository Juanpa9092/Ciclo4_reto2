package reto2_web.reto2_web.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import reto2_web.reto2_web.model.Order;

public interface OrderInterface extends MongoRepository<Order, Integer> {
    
    @Query("{'salesMan.zone': ?0}")
    List<Order> findByZone(final String country);

    @Query("{status: ?0}")
    List<Order> findByStatus(final String status);

    Optional<Order> findTopByOrderByIdDesc();
}
