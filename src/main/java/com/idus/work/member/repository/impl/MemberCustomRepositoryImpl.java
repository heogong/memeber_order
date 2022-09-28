package com.idus.work.member.repository.impl;

import com.idus.work.common.constant.Gender;
import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.entity.Member;
import com.idus.work.member.repository.MemberCustomRepository;
import com.idus.work.order.dto.OrderDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.idus.work.member.entity.QMember.member;

@RequiredArgsConstructor
@Repository
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Member> findByAllMemberByPage(MemberDTO.MemberReq req, Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(
                        member.name.eq(req.getName()),
                        member.email.eq(req.getEmail())
                ).orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
