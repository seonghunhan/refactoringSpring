package com.example.demo.src.post;


import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//Provider : Read의 비즈니스 로직 처리
@RequiredArgsConstructor
@Slf4j
@Service
public class PostProvider {

    private final PostRepository userDao;
    private final JwtService jwtService;


    //    public GetUserFeedRes retrieveUserFeed(int userIdxByjwt, int userIdx) throws BaseException{
//        Boolean isMyFeed = true;
//
//        if(checkUserExist(userIdx) == 0)
//        {
//            throw new BaseException(USERS_EMPTY_USER_ID);
//        }
//
//        try{
//            if(userIdxByjwt != userIdx)
//                isMyFeed = false;
//
//            GetUserInfoRes getUserInfoRes = userDao.selectUserInfo(userIdx);
//            List<GetUserPostsRes> getUserPosts = userDao.selectUserPosts(userIdx);
//            GetUserFeedRes getUsersRes = new GetUserFeedRes(isMyFeed, getUserInfoRes, getUserPosts);
//            return getUsersRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//                    }


//    public GetUserFeedRes getUsersByIdx(int userIdx) throws BaseException{
//        try{
//            GetUserFeedRes getUsersRes = userDao.getUsersByIdx(userIdx);
//            return getUsersRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


//    public int checkEmail(String email) throws BaseException{
//        try{
//            return userDao.checkEmail(email);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkUserExist(int userIdx) throws BaseException{
//        try{
//            return userDao.checkUserExist(userIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }



}
