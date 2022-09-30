package com.idus.work.order;

import com.idus.work.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitOrderApplicationRunner implements ApplicationRunner {
    private final OrderService orderService;

    /**
     * 애플리케이션 구동 시 초기 주문정보 세잍
     * GeneratedValue 커스텀으로 인해 import.sql insert 안됨
     *
     */
    @Override
    public void run(ApplicationArguments args) {
        orderService.initOrderData();
    }
}
