package com.example.demo.src.post.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetUserFeedRes {

    private boolean _isMyFeed;
    private GetUserInfoRes getUserInfo;
    private List<GetUserPostsRes> getUserPosts;  //객체가 아닌 리스트형식 반환


}
