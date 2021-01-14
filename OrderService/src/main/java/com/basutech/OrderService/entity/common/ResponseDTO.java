package com.basutech.OrderService.entity.common;

import com.basutech.OrderService.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private Order order;
    private String transactionId;
    private double amount;
    private String status;
}
