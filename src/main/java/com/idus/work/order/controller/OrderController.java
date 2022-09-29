package com.idus.work.order.controller;

import com.idus.work.order.repository.OrderRepository;
import com.idus.work.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public void initOrderDate() {
        orderService.initOrderData();
    }
}
