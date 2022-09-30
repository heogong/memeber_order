package com.idus.work.member.service;

import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.entity.Member;
import com.idus.work.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * member 등록
     * @param req : name, nickname, email, password ...
     * @return member 객체
     */
    @Transactional
    public MemberDTO.MemberResp createMember(MemberDTO.MemberReq req) {
        if(memberRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new DuplicateKeyException("There are registered users");
        }
        Member member = memberRepository.save(Member.createMember(req, passwordEncoder));
        return MemberDTO.MemberResp.createMemberResp(member);
    }

    /**
     * member 조회
     * @param id : 사용자 고유번호(seq)
     * @return member 객체, order 객체
     */
    @Transactional(readOnly = true)
    public MemberDTO.MemberResp getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new FindException("Not Find User"));
        return MemberDTO.MemberResp.createMemberResp(member);
    }

    /**
     * member 리스트 조회
     * @param req : name(선택), email(선택), 페이징 데이터(선택)
     * @return Page 객체
     */
    @Transactional(readOnly = true)
    public Page<MemberDTO.MemberListResp> getAllMember(MemberDTO.MemberListReq req) {
        Pageable pageable = PageRequest.of(req.getIndex(), req.getPage());
        Long count = memberRepository.findByAllMemberByCount(req);
        List<MemberDTO.MemberListResp> content = memberRepository.findByAllMemberByPage(req, pageable).stream()
                .map(MemberDTO.MemberListResp::createMemberListResp)
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageable, count);
    }
}