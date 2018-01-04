package ruolan.com.friendcircledemo.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ruolan.com.friendcircledemo.model.ImageInfo;

/**
 * Created by wuyinlei on 2018/1/4.
 */

public interface RemoteService {

    @GET
    Call<List<ImageInfo>> getFriendCircleData();

}
