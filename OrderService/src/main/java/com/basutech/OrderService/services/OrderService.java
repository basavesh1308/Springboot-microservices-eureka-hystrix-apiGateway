package com.basutech.OrderService.services;

import com.basutech.OrderService.entity.Order;
import com.basutech.OrderService.entity.common.Payment;
import com.basutech.OrderService.entity.common.RequestDTO;
import com.basutech.OrderService.entity.common.ResponseDTO;
import com.basutech.OrderService.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    OrderRepository orderRepository;

    @Lazy
    @Autowired
    RestTemplate restTemplate;

    /*@Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String END_POINT_URI;*/

    public ResponseDTO saveOrder(RequestDTO request) throws JsonProcessingException {

        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setAmount(order.getPrice());
        payment.setOrderId(order.getId());

        logger.info("received the Order data in request :" + new ObjectMapper().writeValueAsString(order));
        Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payments/doPayment", payment, Payment.class);

        logger.info("received the payment data in response :" + new ObjectMapper().writeValueAsString(paymentResponse));
        orderRepository.save(order);

        return new ResponseDTO(order, paymentResponse.getTransactionId(), paymentResponse.getAmount(), paymentResponse.getPaymentStatus());
    }
}
