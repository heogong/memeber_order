package com.idus.work.member.repository.impl;

import com.idus.work.member.dto.MemberDTO;
import com.idus.work.member.entity.Member;
import com.idus.work.member.repository.MemberCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.idus.work.member.entity.QMember.member;

@RequiredArgsConstructor
@Repository
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Member> findByAllMemberByPage(MemberDTO.MemberReq req, Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(
                        eqString(req.getName()),
                        eqString(req.getEmail())
                ).orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression eqString(String value) {
        return StringUtils.hasText(value) ? member.name.eq(value) : null;
    }
}
