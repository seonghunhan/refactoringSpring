package com.example.demo.src.post.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserInfoRes {

    private String nickName;
    private String name;
    private String profileImgUrl;
    private String introduction;
    private int followerCount;
    private int followingCount;
    private int postCount;
    

}
