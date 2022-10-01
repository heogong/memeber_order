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
        Member member4 = Member.createInitMember(memberService.getMember(4L));
        Member member5 = Member.createInitMember(memberService.getMember(5L));
        Member member6 = Member.createInitMember(memberService.getMember(6L));
        Member member7 = Member.createInitMember(memberService.getMember(7L));

        orderRepository.saveAll(List.of(
                Order.createInitOrder("chanel😎", member1),
                Order.createInitOrder("dior😘", member1),
                Order.createInitOrder("prada😍", member1),
                Order.createInitOrder("burberry😛", member2),
                Order.createInitOrder("gucci😖", member2),
                Order.createInitOrder("celine😭", member2),
                Order.createInitOrder("fendi😕", member3),
                Order.createInitOrder("hugoboss😛", member3),
                Order.createInitOrder("cartier😳", member3),
                Order.createInitOrder("versace😕", member4),
                Order.createInitOrder("paulsmit😖", member4),
                Order.createInitOrder("bvlgari😳", member4),
                Order.createInitOrder("ferragamo😕", member5),
                Order.createInitOrder("swarovski🤬", member5),
                Order.createInitOrder("ck😛", member5),
                Order.createInitOrder("dkny😕", member6),
                Order.createInitOrder("polo😍", member6),
                Order.createInitOrder("hermes😳", member6),
                Order.createInitOrder("daks😕", member7),
                Order.createInitOrder("coach😎", member7),
                Order.createInitOrder("tods😭", member7)
        ));
    }
}
