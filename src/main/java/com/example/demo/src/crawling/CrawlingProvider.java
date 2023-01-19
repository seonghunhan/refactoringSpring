package com.example.demo.src.crawling;

import com.example.demo.response.BaseException;
import com.example.demo.src.crawling.model.GetNewsArticleRes;
import com.example.demo.src.crawling.model.GetTopFiveKeywordsRes;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.response.BaseResponseStatus.DATABASE_ERROR;

//Provider : Read의 비즈니스 로직 처리
@Slf4j
@Service
public class CrawlingProvider {

    private final CrawlingRepository crawlingRepository;
    private final JwtService jwtService;


    @Autowired
    public CrawlingProvider(CrawlingRepository crawlingRepository, JwtService jwtService) {
        this.crawlingRepository = crawlingRepository;
        this.jwtService = jwtService;
    }


    public ArrayList<ArrayList<String>> retrieveNewsList(String keyword, int page) throws BaseException {

        try{
            ArrayList<ArrayList<String>> newsList = new ArrayList<>();
            ArrayList<String> news = new ArrayList<String> ();
//            String keyword =
//            int page = 1;
            int cnt = 0;

            while(true) {

                String url = "https://search.naver.com/search.naver?where=news&sm=tab_pge&query="+keyword+"&sort=0&photo=0&field=0&pd=0&ds=&de=&cluster_rank=0&mynews=0&office_type=0&office_section_code=0&news_office_checked=&nso=so:r,p:all,a:all&start="+page;
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.getElementsByAttributeValue("class", "bx");

                for(int i = 0; i < elements.size() ; i++){
                    if (cnt >= 10){
                        break;
                    }

                    Element element = elements.get(i);
                    Elements validNewsElements = element.getElementsByAttributeValue("class", "news_info");
                    String newsInfoContents = validNewsElements.text();

                    if (newsInfoContents.contains("네이버뉴스")) {
                        //기사 사진
                        Elements photoElements = element.getElementsByAttributeValue("class", "thumb api_get");
                        String picture = photoElements.attr("src");

                        //기사 제목
                        Elements titleElements = element.getElementsByAttributeValue("class", "news_tit");
                        String title = titleElements.attr("title");

                        //기사 간략한 내용
                        Elements briefElements = element.getElementsByAttributeValue("class","api_txt_lines dsc_txt_wrap");
                        String brief = briefElements.text();

                        //기사 url
                        Elements hrefElements = element.getElementsByAttributeValue("class", "info");
                        String href = hrefElements.attr("href");

                        //뉴스 날짜(네이버 뉴스 들가서 날짜 가지고 오기)
                        String url2 = href;
                        Document doc2 = Jsoup.connect(url2).get();
                        Elements elements2 = doc2.getElementsByAttributeValue("id", "ct");
                        Element element2 = elements2.get(0);

                        Elements dateElements2 = element2.getElementsByAttributeValue("class", "media_end_head_info_datestamp_time _ARTICLE_DATE_TIME");
                        String date = dateElements2.attr("data-date-time");

                        news.add(title);
                        news.add(date);
                        news.add(picture);
                        news.add(brief);
                        news.add(href);

                        // 2차원 arrayList 깊은복사
                        newsList.add((ArrayList<String>) news.clone());
                        news.clear();

                        cnt += 1;
                    }
                }
                if (cnt < 10) {
                    page += 10;
                }else {
                    break;
                }
            }
            // 자바 2차원접근 리스트이름.get(index).get(index)
//            System.out.println(newsList.size());
//            System.out.println(newsList);
//            System.out.println(page);
            ArrayList<String> keyword1 = new ArrayList<>();
            keyword1.add(keyword);
            newsList.add(keyword1);
            ArrayList<ArrayList<String>> getNewsList10Res = newsList;
            return getNewsList10Res;


        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetNewsArticleRes getArticleByUrl(String url) throws BaseException {

        try{
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByAttributeValue("id", "ct");

            Element element = elements.get(0);
            // 뉴스 내용
            Elements articleContentsElements2 = element.getElementsByAttributeValue("class","go_trans _article_content");
            articleContentsElements2.select("br").append("\\n");
            String contents = articleContentsElements2.text();


            String s = contents.replaceAll("\\\\n","d");
            int idx = s.indexOf("d d");
            String contents2 = contents.substring(idx + 1);

            String FinalArticle = contents2.replaceAll("\\\\n","\n");

            // 뉴스 타이틀
            Elements elements1 = doc.getElementsByAttributeValue("class", "media_end_head_headline");
            String title = elements1.text();


            // 뉴스 날짜
            Elements elements2 = doc.getElementsByAttributeValue("class", "media_end_head_info_datestamp_time _ARTICLE_DATE_TIME");
            String date = elements2.text();


            return new GetNewsArticleRes(FinalArticle,title,date);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }



    public List<GetTopFiveKeywordsRes> checkFiveKeywords(int userIdx) throws BaseException{
        try{
            return crawlingRepository.selectTopFiveKeywords(userIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }



}
