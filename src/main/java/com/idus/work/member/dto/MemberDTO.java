package com.idus.work.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idus.work.common.constant.Gender;
import com.idus.work.member.entity.Member;
import com.idus.work.order.dto.OrderDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;
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
        @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "한글, 영문 대/소문자만 입력가능")
        @ApiModelProperty(example = "박정진", name = "이름(한글, 영문 대/소문자)")
        private final String name;

        @NotBlank
        @Size(max = 30)
        @Pattern(regexp = "^[a-z]*$", message = "영문 소문자만 입력가능")
        @ApiModelProperty(example = "justdoit", name = "별명(영문 소문자)")
        private final String nickName;

        @Email
        @NotBlank
        @Size(max = 100)
        @ApiModelProperty(example = "just@doit.com")
        private final String email;

        @NotBlank
        @Size(min = 9, message = "최소 9자 이상")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@!%*#?&])[A-Za-z\\d$@!%*#?&]{8,}$",
                message = "영문 대/소문자, 특수문자, 숫자 포함")
        private String password; // 대문자, 소문자, 특수문자, 숫자 각 1개

        @NotBlank
        @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$",
                message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
        @Size(min = 10, max = 20)
        @ApiModelProperty(example = "01000000000")
        private String phoneNumber; // 숫자

        @ApiModelProperty(example = "NONE")
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MemberResp {
        private Long id;
        private String name;
        private String nickName;
        private String phoneNumber;
        private String email;
        private String gender;
        private List<OrderDTO.OrderResp> orderRespList;

        public static MemberResp createMemberResp(Member member) {
            return MemberResp.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .nickName(member.getNickName())
                    .phoneNumber(member.getPhoneNumber())
                    .email(member.getEmail())
                    .gender(member.getGender().getKorGender())
                    .orderRespList(member.getMemberOrderList())
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
                    .lastOrder(OrderDTO.OrderResp.createOrderResp(member.lastOrder()))
                    .build();
        }
    }
}
