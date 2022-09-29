package com.idus.work.member.entity;

import com.idus.work.common.constant.Gender;
import com.idus.work.member.dto.MemberDTO;
import com.idus.work.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
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
    private String name;

    @Column(length = 30)
    private String nickName;
    private String password;
    private String phoneNumber;

    @Column(length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Transient
    private final String ROLE_USER = "ROLE_USER";

    public Order getLastOrder() {
        Order lastOrder = null;
        Optional<Order> order = this.orders.stream().max(Comparator.comparing(Order::getCreateDate));
        if(order.isPresent()) {
            lastOrder = order.get();
        }
        return lastOrder;
    }

    public static Member createMember(MemberDTO.MemberReq req, PasswordEncoder encoder) {
        return Member.builder()
                .name(req.getName())
                .nickName(req.getNickName())
                .password(encoder.encode(req.getPassword()))
                .phoneNumber(req.getPhoneNumber())
                .email(req.getEmail())
                .gender(req.getGender())
                .build();
    }

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
        return List.of(new SimpleGrantedAuthority(ROLE_USER));
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
