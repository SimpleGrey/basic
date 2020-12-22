package tool;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author zhanqian
 * @Date 2020/12/9 11:45
 * @Description TODO
 */
public class OkHttpUtil {

    private static OkHttpClient client = new OkHttpClient();

    public static String post(String url, String token) throws IOException {
        FormBody formBody = new FormBody.Builder()
                .add("originalUrl", "https://mmbiz.qpic.cn/mmbiz_gif/UwhnfjpdCrQyq3C80kb4KmzWEtzI5WsQmkmeRMxTJrN9ibwouaFlbNwuTqAgVOLp5wyWhdqLAicdicnYH4gp3ibrvw/640?wx_fmt=gif&tp=webp&wxfrom=5&wx_lazy=1")
                .add("ossUrlAuthType", "1")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("token", token)
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
