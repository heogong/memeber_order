package com.idus.work.member.entity;

import com.idus.work.common.constant.Gender;
import com.idus.work.order.entity.Order;
import lombok.Getter;

import javax.persistence.*;
import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name; // 한글, 영문 만

    @Column(length = 30)
    private String nickName; // 영문 소문
    private String password; // 대문자, 소문자, 특수문자, 숫자 각 1개
    private Integer phoneNumber; // 숫자

    @Column(length = 100)
    private String email; // 이메일 형식

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Order getLastOrder() {
        Order lastOrder = null;
        Optional<Order> order = this.orders.stream().max(Comparator.comparing(Order::getCreateDate));

        if(order.isPresent()) {
            lastOrder = order.get();
        }
        return lastOrder;
    }
}
