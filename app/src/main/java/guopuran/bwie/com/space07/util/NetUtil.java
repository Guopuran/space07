package guopuran.bwie.com.space07.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {
    //获取网络请求
    public static String geturl(String lujing){
        String result="";
        try {
            //定义地址
            URL url=new URL(lujing);
            //打开连接
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            //请求方法
            urlConnection.setRequestMethod("GET");
            //读取超时
            urlConnection.setReadTimeout(5000);
            //连接超时
            urlConnection.setConnectTimeout(5000);
            if (urlConnection.getResponseCode()==200){
                result=NetUtil.zifu(urlConnection.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    //字节流转换为字符流
    private static String zifu(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader buff=new BufferedReader(inputStreamReader);
        StringBuilder builder=new StringBuilder();
        for (String imp=buff.readLine();imp!=null;imp=buff.readLine()){
            builder.append(imp);
        }
        return builder.toString();
    }
}
