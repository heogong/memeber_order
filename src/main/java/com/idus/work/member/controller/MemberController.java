package com.idus.work.member.controller;

import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    @ApiOperation(value = "멤버 등록", notes = "이름, 별명, 전화번호...")
    public ResponseEntity<MemberDTO.MemberResp> createMember(@RequestBody @Valid MemberDTO.MemberReq req) {
        return new ResponseEntity<>(memberService.createMember(req), HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    @ApiOperation(value = "멤버 조회", notes = "등록된 멤버 id 조회")
    public ResponseEntity<MemberDTO.MemberResp> getMember(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.getMember(id), HttpStatus.OK);
    }

    @GetMapping("/member")
    @ApiOperation(value = "멤버 리스트", notes = "등록된 전체 목록 조회 - 페이징")
    public ResponseEntity<Page<MemberDTO.MemberListResp>> getAllMember(MemberDTO.MemberListReq req) {
        return new ResponseEntity<>(memberService.getAllMember(req), HttpStatus.OK);
    }
}
