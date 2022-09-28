package com.idus.work.member.controller;

import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/{id}")
    public ResponseEntity<MemberDTO.MemberResp> getMember(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.getMember(id), HttpStatus.OK);
    }
}
