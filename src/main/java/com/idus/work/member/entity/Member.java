package com.idus.work.member.entity;

import com.idus.work.common.constant.Gender;
import com.idus.work.member.dto.MemberDTO;
import com.idus.work.order.entity.Order;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import java.lang.module.FindException;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name; // 한글, 영문 만

    @Column(length = 30)
    private String nickName; // 영문 소문
    private String password; // 대문자, 소문자, 특수문자, 숫자 각 1개
    private Integer phoneNumber; // 숫자

    @Column(length = 100)
    private String email; // 이메일 형식

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Order getLastOrder() {
        Order lastOrder = null;
        Optional<Order> order = this.orders.stream().max(Comparator.comparing(Order::getCreateDate));
        if(order.isPresent()) {
            lastOrder = order.get();
        }
        return lastOrder;
    }

//    public static Member createMember(MemberDTO.MemberReq req) {
//        return Member
//
//    }

    // 초기 order 생성 데이터
    public static Member createInitMember(MemberDTO.MemberResp resp) {
        return Member.builder()
                .id(resp.getId())
                .name(resp.getName())
                .nickName(resp.getNickName())
                .phoneNumber(resp.getPhoneNumber())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
