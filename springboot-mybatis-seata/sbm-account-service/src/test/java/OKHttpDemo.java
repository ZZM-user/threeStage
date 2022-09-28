import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class OKHttpDemo {

    /**、OkHttp框架封装UrlConnection对象
     * 请求访问http://www.baidu.com
     *   <dependency>
     *             <groupId>com.squareup.okhttp3</groupId>
     *             <artifactId>okhttp</artifactId>
     *         </dependency>
     */
    @Test
    public void test001() throws IOException {
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder().url("http://www.baidu.com").method("GET",null).build();

        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //同步
        Response execute = call.execute();
        System.out.println(execute.body().string());

//        //4.请求加入调度，重写回调方法 (异步调用)
//        call.enqueue(new Callback() {
//            //请求失败执行的方法
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//            //请求成功执行的方法
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String data = response.body().string();
//                System.out.println(data);
//            }
//        });



    }
    @Test
    public void test02(){
        RestTemplate  restTemplate=new RestTemplate();
        String forObject = restTemplate.getForObject("http://www.baidu.com", String.class);
        System.out.println(forObject);
    }




}
