package ruolan.com.friendcircledemo.utils;

import android.content.Context;

/**
 * Created by wuyinlei on 2018/1/4.
 *
 * @function dp 转 px 工具类
 */

public class DisplayUtil {


    private static float scale;

    public static float getScale(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }


    public static int dip2px(int dipValue) {
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 根据手机分辨率 从px转为dp
     */
    private static int px2dp(int pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }


}
