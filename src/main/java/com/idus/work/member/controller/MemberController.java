package com.idus.work.member.controller;

import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/{id}")
    public ResponseEntity<MemberDTO.MemberResp> getMember(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.getMember(id), HttpStatus.OK);
    }

    @GetMapping("/member/all")
    public ResponseEntity<List<MemberDTO.MemberListResp>> getAllMember(MemberDTO.MemberReq req) {
        return new ResponseEntity<>(memberService.getAllMember(req), HttpStatus.OK);
    }
}
