package ruolan.com.friendcircledemo.net;


import android.view.PixelCopy;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wuyinlei on 2018/1/4.
 *
 * @function
 */

public class RestClient {

    /**
     * 获取OkHttpClient
     */
    private static final class OkHttpHolder {

        private static final int TIME_OUT = 60;

        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        private static final OkHttpClient OK_HTTP_CLIENT = BUILDER
//                .addInterceptor(addInterceptor())
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

        /**
         * 添加拦截器
         *
         * @return Interceptor
         */
        private static Interceptor addInterceptor() {
            return null;
        }


    }

    private static final class RetrofitHolder{
        private static final String BASE_URL = "https://easy-mock.com/mock/5a4eec8edd9300700adaf39b/";

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();

    }

    public static final class RestServiceHolder{
        public static final RemoteService REMOTE_SERVICE = RetrofitHolder.RETROFIT_CLIENT
                .create(RemoteService.class);
    }

}
