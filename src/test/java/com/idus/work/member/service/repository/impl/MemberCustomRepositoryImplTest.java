package com.idus.work.member.service.repository.impl;

import com.idus.work.member.dto.MemberDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import static com.idus.work.member.entity.QMember.member;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberCustomRepositoryImplTest {
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void findByAllMemberByPage() {
        String name = "자바";
        String email = "spring@boot.com";

        jpaQueryFactory
                .selectFrom(member)
                .where(
                        StringUtils.hasText(name) ? member.name.eq(name) : null,
                        StringUtils.hasText(email) ? member.email.eq(email) : null
                )
                .orderBy(member.id.desc())
                .offset(0)
                .limit(5)
                .fetch();
    }

    @Test
    void findByAllMemberByCount() {
        String name = "자바";
        String email = "spring@boot.com";

        jpaQueryFactory
                .select(member.count())
                .from(member)
                .where(
                        StringUtils.hasText(name) ? member.name.eq(name) : null,
                        StringUtils.hasText(email) ? member.email.eq(email) : null
                )
                .fetchOne();
    }

}