package com.idus.work.order.dto;

import com.idus.work.order.entity.Order;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

public class OrderDTO {

    @Builder
    @Getter
    public static class OrderResp {
        private String orderNumber;
        private String orderName;
        private Date createDate;

        public static OrderResp createOrderResp(Order order) {
            return OrderResp.builder()
                    .orderName(order.getOrderNumber())
                    .orderName(order.getOrderName())
                    .createDate(order.getCreateDate())
                    .build();
        }
    }
}
