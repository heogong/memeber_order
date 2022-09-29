package com.idus.work.member.service;

import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.entity.Member;
import com.idus.work.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public MemberDTO.MemberResp createMember(MemberDTO.MemberReq req) {

        if(memberRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new DuplicateKeyException("There are registered users");
        }

        Member member = memberRepository.save(Member.createMember(req));
        return MemberDTO.MemberResp.createMemberResp(member);
    }

    @Transactional(readOnly = true)
    public MemberDTO.MemberResp getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new FindException("Not Find User"));
        return MemberDTO.MemberResp.createMemberRespWithOrder(member);
    }

    @Transactional(readOnly = true)
    public List<MemberDTO.MemberListResp> getAllMember(MemberDTO.MemberListReq req) {
        Pageable pageable = PageRequest.of(req.getIndex(), req.getPage());

        return memberRepository.findByAllMemberByPage(req, pageable).stream()
                .map(MemberDTO.MemberListResp::createMemberListResp)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Find User"));
    }
}