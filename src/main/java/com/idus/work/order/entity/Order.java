package com.idus.work.order.entity;

import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "orders")
public class Order {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @GeneratedValue(generator = "orderNumber")
    @GenericGenerator(name = "orderNumber", strategy = "com.idus.work.order.entity.OrderNumberGenerator")
    private String orderNumber; // 중복불가 영문자 대문자

    private String orderName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public static Order createInitOrder(String name, Member member) {
        return new Order(name, member);
    }

    private Order(String orderName, Member member) {
        this.orderName = orderName;
        this.createDate = new Date();
        this.member = member;
    }
}
