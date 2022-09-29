package com.idus.work.member.dto;

import com.idus.work.common.constant.Gender;
import com.idus.work.member.entity.Member;
import com.idus.work.order.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;

public class MemberDTO {

    private static final Integer DEFAULT_INDEX_SIZE = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 5;

//    @AllArgsConstructor
    @Getter
    public static class MemberReq {
        private final Integer index;
        private final Integer page;
        private final String name;
        private final String email;

        public MemberReq(Integer index, Integer page, String name, String email) {
            this.index = index == null ? DEFAULT_INDEX_SIZE : index;
            this.page = page == null ? DEFAULT_PAGE_SIZE : page;
            this.name = name;
            this.email = email;
        }
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
                    .lastOrder(OrderDTO.OrderResp.createOrderResp(member.getLastOrder())) // TODO lastorder null(주문내역 없을 시) 처리
                    .build();
        }
    }
}
