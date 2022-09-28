package com.idus.work.member.service;

import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.entity.Member;
import com.idus.work.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.FindException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberDTO.MemberResp getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new FindException("Not Find User"));
        return MemberDTO.MemberResp.createMemberResp(member);
    }

    @Transactional(readOnly = true)
    public List<MemberDTO.MemberListResp> getAllMember(MemberDTO.MemberReq req) {
        Pageable pageable = PageRequest.of(0, 5);

        return memberRepository.findByAllMemberByPage(req, pageable).stream()
                .map(MemberDTO.MemberListResp::createMemberListResp)
                .collect(Collectors.toList());
    }
}