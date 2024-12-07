package com.navi.nbcampjpaspringtestexample.api.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

//    private PaymentRepository paymentRepository;
//
//    private PaymentClient payment;

    public void pay(Long orderId, Long amount) {
        // ...
        System.out.println("Order ID: " + orderId + ", Amount: " + amount + " - Payment processed.");
    }

}
