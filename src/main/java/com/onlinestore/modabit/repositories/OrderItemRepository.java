package com.onlinestore.modabit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.modabit.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
