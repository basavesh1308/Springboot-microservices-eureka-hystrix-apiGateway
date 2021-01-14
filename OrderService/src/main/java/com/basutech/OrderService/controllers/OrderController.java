package com.basutech.OrderService.controllers;


import com.basutech.OrderService.entity.Order;
import com.basutech.OrderService.entity.common.RequestDTO;
import com.basutech.OrderService.entity.common.ResponseDTO;
import com.basutech.OrderService.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/bookOrder")
    public ResponseDTO saveOrder(@RequestBody RequestDTO request) throws JsonProcessingException {
        return orderService.saveOrder(request);
    }

}
