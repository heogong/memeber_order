package com.idus.work.member.controller;

import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<MemberDTO.MemberResp> createMember(@RequestBody @Valid MemberDTO.MemberReq req) {
        return new ResponseEntity<>(memberService.createMember(req), HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<MemberDTO.MemberResp> getMember(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.getMember(id), HttpStatus.OK);
    }

    @GetMapping("/member/all")
    public ResponseEntity<List<MemberDTO.MemberListResp>> getAllMember(MemberDTO.MemberListReq req) {
        return new ResponseEntity<>(memberService.getAllMember(req), HttpStatus.OK);
    }
}
