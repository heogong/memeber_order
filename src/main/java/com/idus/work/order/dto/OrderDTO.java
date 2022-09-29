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
        private String productName;
        private Date createDate;

        public static OrderResp createOrderResp(Order order) {
            return order == null ? null :
                    OrderResp.builder()
                            .orderNumber(order.getOrderNumber())
                            .productName(order.getProductName())
                            .createDate(order.getCreateDate())
                            .build();
        }
    }
}
