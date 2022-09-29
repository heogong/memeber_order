package com.idus.work.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idus.work.common.constant.Gender;
import com.idus.work.member.entity.Member;
import com.idus.work.order.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class MemberDTO {

    private static final Integer DEFAULT_INDEX_SIZE = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 5;

    @AllArgsConstructor
    @Getter
    public static class MemberReq {

        @NotBlank
        @Size(max = 20)
        private final String name;

        @NotBlank
        @Size(max = 30)
        private final String nickName;

        @Email
        @NotBlank
        @Size(max = 100)
        private final String email;

        @NotBlank
        @Size(min = 9, message = "최소 9자 이상")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$",
                message = "대소문자, 특수문자, 숫자 포함")
        private String password; // 대문자, 소문자, 특수문자, 숫자 각 1개

        @NotBlank
        @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$",
                message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
        @Size(min = 10, max = 20)
        private String phoneNumber; // 숫자

        private Gender gender;
    }

    @Getter
    public static class MemberListReq {
        private final Integer index;
        private final Integer page;
        private final String name;
        private final String email;

        public MemberListReq(Integer index, Integer page, String name, String email) {
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
        private String phoneNumber;
        private String email;
        @JsonIgnore
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
                    .orderRespList(member.getOrders() != null ? member.getOrders().stream()
                            .map(OrderDTO.OrderResp::createOrderResp)
                            .collect(Collectors.toList()) : null
                    )
                    .build();
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
