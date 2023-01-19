package com.example.demo.src.crawling;


import com.example.demo.response.BaseException;
import com.example.demo.src.crawling.model.GetNewsArticleReq;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.response.BaseResponseStatus.DATABASE_ERROR;

// Service Create, Update, Delete 의 로직 처리
@Slf4j
@Service
public class CrawlingService {

    private final CrawlingRepository crawlingRepository;
    private final CrawlingProvider crawlingProvider;
    private final JwtService jwtService;


    @Autowired
    public CrawlingService(CrawlingRepository crawlingRepository, CrawlingProvider crawlingProvider, JwtService jwtService) {
        this.crawlingRepository = crawlingRepository;
        this.crawlingProvider = crawlingProvider;
        this.jwtService = jwtService;

    }

    public void stackKeyword(GetNewsArticleReq getNewsArticleReq) throws  BaseException{

        try{

            crawlingRepository.updateKeywordStack(getNewsArticleReq);

            return;
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
