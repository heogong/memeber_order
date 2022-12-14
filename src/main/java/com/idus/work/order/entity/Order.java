package com.idus.work.order.entity;

import com.idus.work.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "orderNumber")
    @GenericGenerator(name = "orderNumber", strategy = "com.idus.work.order.entity.OrderNumberGenerator")
    @Column(length = 12)
    private String orderNumber;

    @Column(length = 100, nullable = false)
    private String productName;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public static Order createInitOrder(String name, Member member) {
        return new Order(name, member);
    }

    private Order(String productName, Member member) {
        this.productName = productName;
        this.createDate = LocalDateTime.now();
        this.member = member;
    }
}
