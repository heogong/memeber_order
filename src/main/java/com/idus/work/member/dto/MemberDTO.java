package com.idus.work.member.dto;

import com.idus.work.common.constant.Gender;
import com.idus.work.member.entity.Member;
import com.idus.work.order.dto.OrderDTO;
import com.idus.work.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class MemberDTO {

    @AllArgsConstructor
    @Getter
    public static class MemberReq {
        private String name;
        private String email;
    }

    @Builder
    @Getter
    public static class MemberResp {
        private Long id;
        private String name;
        private String nickName;
        private Integer phoneNumber;
        private String email;
        private Gender gender;
        private List<OrderDTO.OrderResp> orderRespList;

        public static MemberResp createMemberResp(Member member) {
            return MemberResp.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .nickName(member.getNickName())
                    .phoneNumber(member.getPhoneNumber())
                    .email(member.getEmail())
                    .gender(member.getGender())
                    .orderRespList(member.getOrders().stream()
                            .map(OrderDTO.OrderResp::createOrderResp)
                            .collect(Collectors.toList())
                    ).build();
        }
    }

    @Builder
    @Getter
    public static class MemberListResp {
        private Long id;
        private String name;
        private String email;
        private OrderDTO.OrderResp lastOrder;

        public static MemberListResp createMemberListResp(Member member) {
            return MemberListResp.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .lastOrder(OrderDTO.OrderResp.createOrderResp(member.getLastOrder()))
                    .build();
        }

    }
}
