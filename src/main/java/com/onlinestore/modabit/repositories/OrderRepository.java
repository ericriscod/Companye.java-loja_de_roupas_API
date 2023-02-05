package com.onlinestore.modabit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.modabit.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
