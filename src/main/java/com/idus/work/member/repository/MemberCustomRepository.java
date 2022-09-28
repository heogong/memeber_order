package com.idus.work.member.repository;

import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface MemberCustomRepository {

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    List<Member> findByAllMemberByPage(MemberDTO.MemberReq searchDate, Pageable pageable);
}
