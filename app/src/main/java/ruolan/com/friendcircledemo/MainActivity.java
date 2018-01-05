package ruolan.com.friendcircledemo;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ruolan.com.friendcircledemo.model.FriendCircleModel;
import ruolan.com.friendcircledemo.model.HttpResult;
import ruolan.com.friendcircledemo.net.RestClient;
import ruolan.com.friendcircledemo.utils.imageloader.ImageLoader;

public class MainActivity extends AppCompatActivity {

    TwinklingRefreshLayout mRefreshLayout;
    CoordinatorLayout mCoordinatorLayout;
    AppBarLayout mAppBarLayout;
    CollapsingToolbarLayout mToolbarLayout;

    AppCompatImageView mCircleBanner;
    AppCompatImageView mCircleAvatar;
    AppCompatTextView mCircleName;

    RelativeLayout mActionMessage;
    RelativeLayout mActionPublish;


    RecyclerView mRvCircleView;

    FriendCircleAdapter mFriendCircleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();


    }

    private void initData() {
        RestClient.RestServiceHolder.REMOTE_SERVICE.getFriendCircleData()
                .enqueue(new Callback<HttpResult<FriendCircleModel>>() {
                    @Override
                    public void onResponse(Call<HttpResult<FriendCircleModel>> call, Response<HttpResult<FriendCircleModel>> response) {
                        if (response.isSuccessful()) {
                            FriendCircleModel friendCircleModel = response.body().getData();
                            parseCircleData(friendCircleModel);
                        }
                    }

                    @Override
                    public void onFailure(Call<HttpResult<FriendCircleModel>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 解析数据
     *
     * @param friendCircleModel FriendCircleModel
     */
    private void parseCircleData(FriendCircleModel friendCircleModel) {
        FriendCircleModel.UserinfoBean userinfo = friendCircleModel.getUserinfo();
        List<FriendCircleModel.ListBean> imageList = friendCircleModel.getList();
//        ImageLoader.load(friendCircleModel.getBgimage(), mCircleBanner);
//        mCircleName.setText(userinfo.getNick_name());
//        ImageLoader.load(userinfo.getHeadpic(), mCircleAvatar);
        if (imageList != null && imageList.size() > 0) {
            mFriendCircleAdapter = new FriendCircleAdapter(imageList);
            mRvCircleView.setLayoutManager(new LinearLayoutManager(this));
            mRvCircleView.setAdapter(mFriendCircleAdapter);
        }
    }

    private void initView() {

//        mRefreshLayout = findViewById(R.id.refresh_layout);
//        mCoordinatorLayout = findViewById(R.id.coord_container);
//        mAppBarLayout = findViewById(R.id.appbar_layout);
//        mToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
//        mCircleBanner = findViewById(R.id.circle_banner);
//        mCircleAvatar = findViewById(R.id.circle_avatar);
//        mCircleName = findViewById(R.id.circle_name_txt);
//        mActionMessage = findViewById(R.id.action_mindcirrcle_message);
//        mActionPublish = findViewById(R.id.action_mindcirrcle_publish);
        mRvCircleView = findViewById(R.id.recycler_view);
    }
}
