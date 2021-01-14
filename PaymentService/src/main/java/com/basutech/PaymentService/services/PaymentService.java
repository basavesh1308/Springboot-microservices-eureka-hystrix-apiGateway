package com.basutech.PaymentService.services;

import com.basutech.PaymentService.entity.Payment;
import com.basutech.PaymentService.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    PaymentRepository paymentRepository;

    public Payment doPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());

        logger.info("received the payment data in request :" + new ObjectMapper().writeValueAsString(payment));
        return paymentRepository.save(payment);
    }

    public String paymentProcessing(){
        // this api call to third party service for payment gateway eg: paytm, phonePe, etc...

        return new Random().nextBoolean() ? "success" : "failure";
    }

}
