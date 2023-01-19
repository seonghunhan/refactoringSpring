package com.example.demo.src.post;


import com.example.demo.response.BaseException;
import com.example.demo.src.post.model.PostPostsReq;
import com.example.demo.src.post.model.PostPostsRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.demo.response.BaseResponseStatus.DATABASE_ERROR;

// Service Create, Update, Delete 의 로직 처리
@RequiredArgsConstructor
@Slf4j
@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostProvider userProvider;
    private final JwtService jwtService;


    // 컨트롤러로 리턴하는값이 PostPostsRes였음
    public PostPostsRes createPosts(int userIdx, PostPostsReq postPostsReq) throws  BaseException{ //컨트롤러에서 userIdx랑 바디값을 받았음)

        try{
            int postIdx = postRepository.insertPosts(userIdx,postPostsReq.getContent()); //여기에는 idx와 게시글내용만 넣겠다
            for (int i=0; i<postPostsReq.getPostImgUrls().size(); i++){ // 이미지 사이즈만큼 반복문 돌리고 insertPostImgs에 그 갯수만큼 넣는것
                postRepository.insertPostImgs(postIdx, postPostsReq.getPostImgUrls().get(i)); // 반복문을 돌면서 한개씩 db에 저장되는것
            }
            return new PostPostsRes(postIdx); // PostPostsRes객체에 postIdx를 넣어서 보내주기
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }


//    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
//        // 이메일 중복 확인
//        if(userProvider.checkEmail(postUserReq.getEmail()) ==1){
//            throw new BaseException(POST_USERS_EXISTS_EMAIL);
//        }
//
//        String pwd;
//        try{
//            //암호화
//            pwd = new SHA256().encrypt(postUserReq.getPassword());  postUserReq.setPassword(pwd);
//        } catch (Exception ignored) {
//            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
//        }
//        try{
//            int userIdx = postDao.createUser(postUserReq);
//            //jwt 발급.
//            // TODO: jwt는 다음주차에서 배울 내용입니다!
//            String jwt = jwtService.createJwt(userIdx);
//            return new PostUserRes(jwt,userIdx);
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }

//    public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {
//        try{
//            int result = postDao.modifyUserName(patchUserReq);
//            if(result == 0){
//                throw new BaseException(MODIFY_FAIL_USERNAME);
//            }
//        } catch(Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }

}
