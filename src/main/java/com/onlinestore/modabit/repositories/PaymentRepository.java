package com.onlinestore.modabit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.modabit.entities.PaymentMethod;

public interface PaymentRepository extends JpaRepository<PaymentMethod, Long>{

}
