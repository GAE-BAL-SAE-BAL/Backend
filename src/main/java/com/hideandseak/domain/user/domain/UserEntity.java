package com.hideandseak.domain.user.domain;

import com.hideandseak.domain.user.domain.enums.AdultAccess;
import com.hideandseak.domain.user.domain.enums.Role;
import com.hideandseak.domain.user.domain.enums.Subscribe;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userAccount;

    @Column(nullable = false)
    private String password;

    @Column
    private AdultAccess access;

    @Column
    private String anything;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Subscribe subscribe;

    @Builder
    public UserEntity(String userAccount, String password, String anything) {
        this.userAccount = userAccount;
        this.password = password;
        this.access = AdultAccess.NO;
        this.anything = anything;
        this.role = Role.USER;
        this.subscribe = Subscribe.X;
    }

    public void fixPassword(String password) {
        this.password = password;
    }

    public void subscribe(){
        this.subscribe = Subscribe.O;
    }

    public void cancelSubscribe(){
        this.subscribe = Subscribe.X;
    }
}
