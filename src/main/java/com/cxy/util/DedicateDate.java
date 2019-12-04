package com.cxy.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author: ChenXianyong
 * @description: 抓取图片
 * @date: 2019/11/21 10:26
 */
public class DedicateDate {

  public static void main(String[] args) throws IOException {
//    getPic();
    getImage();
  }

  public static void getPic() throws IOException {

    CloseableHttpClient httpClient = HttpClients.createDefault();

    HttpGet httpGet = new HttpGet("https://book.yunzhan365.com/xnir/afrj/mobile/index.html");

    CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
    HttpEntity httpEntity = httpResponse.getEntity();
    String response = EntityUtils.toString(httpEntity, "utf-8");

    Document document = Jsoup.parse(response);
    Elements elements = document.select(".img_box img");

    for (int i = 0; i < 2; i++) {
      Element element = elements.get(i);

      String url = element.attr("src");

      HttpGet picHttpGet = new HttpGet(url);

      CloseableHttpResponse picHttpResponse = httpClient.execute(picHttpGet);

      HttpEntity picEntity = picHttpResponse.getEntity();

      InputStream inputStream = picEntity.getContent();

      FileUtils.copyToFile(inputStream, new File("L://image//"+i+".jpg"));
      picHttpResponse.close();


    }
    httpResponse.close();
    httpClient.close();
  }


  public static void getImage() throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();

    for (int i = 1; i < 139; i++) {

      String url = "https://book.yunzhan365.com/xnir/afrj/files/mobile/"+i+".jpg";

      HttpGet picHttpGet = new HttpGet(url);

      CloseableHttpResponse picHttpResponse = httpClient.execute(picHttpGet);

      HttpEntity picEntity = picHttpResponse.getEntity();

      InputStream inputStream = picEntity.getContent();

      FileUtils.copyToFile(inputStream, new File("L://image//"+i+".jpg"));
      picHttpResponse.close();


    }
    httpClient.close();
  }

}
