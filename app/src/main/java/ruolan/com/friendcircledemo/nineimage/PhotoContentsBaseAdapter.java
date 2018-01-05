package ruolan.com.friendcircledemo.nineimage;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ruolan.com.friendcircledemo.model.ImageInfo;


/**
 * Created by 大灯泡 on 2016/11/9.
 * <p>
 * function
 */

public abstract class PhotoContentsBaseAdapter {


    private PhotoAdapterObservable mObservable = new PhotoAdapterObservable();


    public void registerDataSetObserver(PhotoBaseDataObserver observer) {
        mObservable.registerObserver(observer);

    }

    public void unregisterDataSetObserver(PhotoBaseDataObserver observer) {
        mObservable.unregisterObserver(observer);
    }

    public void notifyDataChanged() {
        mObservable.notifyChanged();
        mObservable.notifyInvalidated();
    }


    /**
     * @param convertView ImageView
     * @param parent      ViewGroup
     * @param position    位置
     * @param videoTrue   是否是视频标志位   只有是单张图片并且是视频的时候为true
     * @param longPic     是否是长图标志(只有单张图片的时候会有  默认是false)
     * @param videoTime   视频时长
     * @return ImageView
     */
    public abstract ImageView onCreateView(ImageView convertView,
                                           ViewGroup parent,
                                           int position,
                                           boolean videoTrue,
                                           boolean longPic,
                                           String videoTime);

    public abstract void onBindData(int position, @NonNull ImageView convertView);

    public abstract int getCount();

    public abstract List<ImageInfo> getDatas();
}
