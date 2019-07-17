package com.maciej.tacocloudspringinaction.data.springdata.repositories;

import com.maciej.tacocloudspringinaction.data.springdata.Order;
import com.maciej.tacocloudspringinaction.security.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    // Examples of custom queries
    List<Order> findByDeliveryZip(String deliveryZip);

    List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
