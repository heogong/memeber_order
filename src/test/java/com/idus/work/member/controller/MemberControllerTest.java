package com.idus.work.member.controller;

import com.google.gson.Gson;
import com.idus.work.common.constant.Gender;
import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.entity.Member;
import com.idus.work.member.service.MemberService;
import com.idus.work.order.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
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
                0 ,5, memberData.getName(), memberData.getEmail()
        );
    }
    @Test
    @DisplayName("멤버 등록 api 테스트")
    void createMember() throws Exception {
        Gson gson = new Gson();
        String content = gson.toJson(memberReq);

        mockMvc.perform(
                post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                )
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("멤버 조회 api 테스트")
    void getMember() throws Exception {
        long id = 1L;

        mockMvc.perform(
                get("/member/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
    @Test
    @DisplayName("멤버 리스트 조회 api 테스트")
    void getAllMember() throws Exception {
        Gson gson = new Gson();
        String content = gson.toJson(listReq);

        mockMvc.perform(
                get("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isOk());
    }
}