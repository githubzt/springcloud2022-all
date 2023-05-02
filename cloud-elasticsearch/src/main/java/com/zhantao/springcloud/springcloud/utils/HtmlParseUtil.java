package com.zhantao.springcloud.springcloud.utils;

import com.zhantao.springcloud.springcloud.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月14日 13:35
 */
@Component
public class HtmlParseUtil {
    public static void main(String[] args) throws IOException {
        HtmlParseUtil htmlParseUtil = new HtmlParseUtil();
        htmlParseUtil.parseJD("vue").forEach(System.out::println);
    }

    public List<Content> parseJD(String keywords) throws IOException {
        /** 获取请求： https://search.jd.com/Search?keyword=java **/
        String url = "https://search.jd.com/Search?keyword=" + keywords;
        //解析网页  返回的Document就是浏览器Document对象
        Document document = Jsoup.parse(new URL(url), 30000);
        //所有的js中都可以使用的方法，这里都能用
        Element element = document.getElementById("J_goodsList");
        //System.out.println(element.html()); //展示标签，查看要解析的标签名
        //获取所有的li标签
        Elements elements = element.getElementsByTag("li");
        ArrayList<Content> goodList = new ArrayList<>();
        //获取元素中的内容
        for (Element el : elements) {
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            //System.out.println("##################################");
            //System.out.println(img);System.out.println(price);System.out.println(title);
            Content content = new Content();
            content.setImg(img);content.setPrice(price);content.setTitle(title);
            goodList.add(content);
        }
        return goodList;
    }

}