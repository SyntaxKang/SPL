package com.spl.spl.dto.users;

import org.springframework.security.core.GrantedAuthority;


public enum UserAuthority implements GrantedAuthority {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    String authority;


    UserAuthority(String authority){
        this.authority=authority;
    }


    @Override
    public String getAuthority(){

        return authority;
    }



}
