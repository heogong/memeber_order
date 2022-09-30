package com.idus.work.member.service;

import com.idus.work.common.constant.Gender;
import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.entity.Member;
import com.idus.work.member.repository.MemberRepository;
import com.idus.work.order.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    MemberService memberService;

    Member memberData;
    MemberDTO.MemberReq memberReq;
    MemberDTO.MemberListReq listReq;

    @BeforeEach
    public void beforeEach() {
        memberData = Member.builder()
                .name("박정진")
                .nickName("justdoit")
                .password("Qqqqwwweerr1!")
                .phoneNumber("01000000000")
                .email("just@doit.com")
                .gender(Gender.MALE)
                .orders(List.of(Order.createInitOrder("boot😎", null)))
                .build();

        memberReq = new MemberDTO.MemberReq(
                memberData.getName(),
                memberData.getNickName(),
                memberData.getEmail(),
                memberData.getPassword(),
                memberData.getPhoneNumber(),
                memberData.getGender()
        );

        listReq = new MemberDTO.MemberListReq(
                0, 5, memberData.getName(), memberData.getEmail()
        );
    }

    @Test
    @DisplayName("멤버 등록 로직 테스트")
    void createMember() {
        Member.createMember(memberReq, passwordEncoder);
        given(memberRepository.save(any())).willReturn(memberData);

        MemberDTO.MemberResp memberResp = memberService.createMember(memberReq);

        assertThat(memberResp.getEmail()).isEqualTo(memberData.getEmail());
        assertThat(memberResp.getName()).isEqualTo(memberData.getName());
    }

    @Test
    @DisplayName("멤버 조회 로직 테스트")
    void getMember() {

        given(memberRepository.findById(any())).willReturn(Optional.of(memberData));

        MemberDTO.MemberResp memberResp = memberService.getMember(1L);

        assertThat(memberResp.getEmail()).isEqualTo(memberData.getEmail());
        assertThat(memberResp.getName()).isEqualTo(memberData.getName());
    }

    @Test
    @DisplayName("멤버 리스트 조회 로직 테스트")
    void getAllMember() {
        given(memberRepository.findByAllMemberByCount(any())).willReturn(7L);
        given(memberRepository.findByAllMemberByPage(any(), any())).willReturn(List.of(memberData));

        Page<MemberDTO.MemberListResp> result = memberService.getAllMember(listReq);

        assertThat(result.getTotalElements()).isEqualTo(7L);
        assertThat(result.getSize()).isEqualTo(5L);
        assertThat(result.getContent().get(0).getEmail()).isEqualTo(memberData.getEmail());
    }

}