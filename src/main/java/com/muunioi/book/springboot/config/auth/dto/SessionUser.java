package com.muunioi.book.springboot.config.auth.dto;

import com.muunioi.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    //인증된 사용자 정보만 필요하기 때문에 3가지만 있으면 됨
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
