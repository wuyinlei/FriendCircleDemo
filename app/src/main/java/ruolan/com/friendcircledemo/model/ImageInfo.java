package ruolan.com.friendcircledemo.model;

import android.text.TextUtils;

/**
 * Created by wuyinlei on 2018/1/4.
 *
 * @function 图片相关信息model
 */

public class ImageInfo extends BaseModel{

    private String thumbnailUrl;  //缩略图   也就是小图
    private String bigImageUrl;  //大图  用于展示使用
    private String name;
    private int imageViewHeight;  //单张图片的高度
    private int imageViewWidth;   //单张图片的宽度
    private int imageViewX;
    private int imageViewY;
    private String livelength;  //视频时长
    private boolean isVideo = false;  //是否是视频类型
    private boolean isLongPic = false;  //是否是单张图片的长图类型
    private boolean singlePic = false;
    private String videopath;  //视频地址


    public void setSinglePic(boolean singlePic) {
        this.singlePic = singlePic;
    }

    public boolean isSinglePic() {
        return singlePic;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getBigImageUrl() {
        return bigImageUrl;
    }

    public void setBigImageUrl(String bigImageUrl) {
        this.bigImageUrl = bigImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageViewHeight() {
        return imageViewHeight;
    }

    public void setImageViewHeight(int imageViewHeight) {
        this.imageViewHeight = imageViewHeight;
    }

    public int getImageViewWidth() {
        return imageViewWidth;
    }

    public void setImageViewWidth(int imageViewWidth) {
        this.imageViewWidth = imageViewWidth;
    }

    public int getImageViewX() {
        return imageViewX;
    }

    public void setImageViewX(int imageViewX) {
        this.imageViewX = imageViewX;
    }

    public int getImageViewY() {
        return imageViewY;
    }

    public void setImageViewY(int imageViewY) {
        this.imageViewY = imageViewY;
    }

    public String getLivelength() {
        return livelength;
    }

    public void setLivelength(String livelength) {
        this.livelength = livelength;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public boolean isLongPic() {
        return isLongPic;
    }

    public void setLongPic(boolean longPic) {
        isLongPic = longPic;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }
}
