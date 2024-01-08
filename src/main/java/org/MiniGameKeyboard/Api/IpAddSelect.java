package org.MiniGameKeyboard.Api;

import net.sf.json.JSONObject;
import org.MiniGameKeyboard.FunctionInterface.OutFunctionalInterface;
import org.MiniGameKeyboard.Panel.Msg;
import org.MiniGameKeyboard.Thread.Retry_Thread;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.management.timer.Timer;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Sun
 * @apiNote "ip地址查询"
 */
public class IpAddSelect {
    private static String responseString;
    public static String getAdd(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response;
        response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        // 解析响应数据
        responseString = EntityUtils.toString(entity);
        // 关闭HttpClient连接
        httpClient.close();
        return String.valueOf(JSONObject.fromObject(responseString));
    }
    public static String getArea(JLabel label) {
        JSONObject msg;
        String country;
        String province;
        String city;
        String area;
        List<String>list;
        List<String>newList;
        try {
            msg = JSONObject.fromObject(IpAddSelect.getAdd("https://ip.useragentinfo.com/json"));
            country = msg.getString("country");//国家
            province = msg.getString("province");//省
            city = msg.getString("city");//城市
            area = msg.getString("area");//区域
            list = Arrays.asList(country,province,city,area);
            newList=list.stream()
                    .filter(s -> s != null && !s.equalsIgnoreCase(""))
                    .collect(Collectors.toList());
            if (newList.isEmpty())return null;
            else return newList.get(newList.size()-1);
        } catch (IOException e) {
            Msg.hit = "网络错误";
            label.repaint();
            throw new RuntimeException(e);
        }
    }
}
