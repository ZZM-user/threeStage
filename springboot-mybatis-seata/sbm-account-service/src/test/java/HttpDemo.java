import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpDemo {

    /**、JDK原生态的 http访问
     * 请求访问http://www.baidu.com
     */
    @Test
    public void test001() throws IOException {

        //1、URL地址
        URL url = new URL("http://www.baidu.com");
        //2、打开连接
        URLConnection urlConnection = url.openConnection();
        //3、转换为HttpUrlConnection
        HttpURLConnection  httpURLConnection= (HttpURLConnection) urlConnection;

        //4、设定连接超时时间
        httpURLConnection.setConnectTimeout(1000*15);
        //5、得到返回状态码 200
        int responseCode = httpURLConnection.getResponseCode();
        if(responseCode!=200){
            System.out.println("连接异常");
        }else{
            InputStream inputStream = httpURLConnection.getInputStream();
            String html=inputStreamToString(inputStream);
            System.out.println(html);
        }
    }

    private String inputStreamToString(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer=new StringBuffer();
        //字节流转换成字符流
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            stringBuffer.append(line);
        }
        br.close();
        return stringBuffer.toString();

    }


}
