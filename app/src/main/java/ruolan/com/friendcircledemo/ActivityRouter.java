package ruolan.com.friendcircledemo;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import ruolan.com.friendcircledemo.model.ImageInfo;

/**
 * Created by wuyinlei on 2018/1/5.
 *
 * @function
 */

public class ActivityRouter {

    public static void Router(List<ImageInfo> imageInfos, int position, Context context){
        if (imageInfos.size() == 1 && imageInfos.get(0).isVideo()){
            //跳转到播放界面
            Toast.makeText(context, "视频播放界面", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "图片大图查看界面", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent();
//            intent.setClass( )
//            Bundle bundle = new Bundle();
//            bundle.putString("position", String.valueOf(position));
//            bundle.putSerializable("imageinfo", (Serializable) imageInfos);
//            intent.putExtras(bundle);
//            context.startActivity(intent);
        }
    }

}
