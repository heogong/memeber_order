package com.idus.work.order.service;

import com.idus.work.member.entity.Member;
import com.idus.work.member.service.MemberService;
import com.idus.work.order.entity.Order;
import com.idus.work.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;

    /**
     * 구동 시 초기 주문 정보 세팅
     */
    @Transactional
    public void initOrderData() {
        Member member1 = Member.createInitMember(memberService.getMember(1L));
        Member member2 = Member.createInitMember(memberService.getMember(2L));
        Member member3 = Member.createInitMember(memberService.getMember(3L));

        orderRepository.saveAll(List.of(
                Order.createInitOrder("boot😎", member1),
                Order.createInitOrder("webflux😘", member1),
                Order.createInitOrder("mongodb😍", member1),
                Order.createInitOrder("mongodb😛", member2),
                Order.createInitOrder("fun😖", member2),
                Order.createInitOrder("suspend😭", member2),
                Order.createInitOrder("Starlette😕", member3),
                Order.createInitOrder("Pydantic🤬", member3),
                Order.createInitOrder("pip😳", member3)
        ));
    }
}
