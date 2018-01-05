package ruolan.com.friendcircledemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ruolan.com.friendcircledemo.model.FriendCircleModel;
import ruolan.com.friendcircledemo.model.ImageInfo;
import ruolan.com.friendcircledemo.nineimage.ImageWrapper;
import ruolan.com.friendcircledemo.nineimage.PhotoContents;
import ruolan.com.friendcircledemo.nineimage.PhotoContentsBaseAdapter;
import ruolan.com.friendcircledemo.utils.imageloader.ImageLoader;

/**
 * Created by wuyinlei on 2018/1/5.
 *
 * @function
 */

public class FriendCircleAdapter extends RecyclerView.Adapter<FriendCircleAdapter.ViewHolder> {


    List<FriendCircleModel.ListBean> imageList = new ArrayList<>();
    InnerContainerAdapter mInnerContainerAdapter;

    public FriendCircleAdapter(List<FriendCircleModel.ListBean> imageList) {
        this.imageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.circle_image_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<ImageInfo> imageInfos = new ArrayList<>();
        FriendCircleModel.ListBean listBean = imageList.get(position);

        if (!TextUtils.isEmpty(listBean.getVideo().getDynamiclive())) {
            imageInfos.add(parseImageInfo(listBean));
        } else if (listBean.getPiclist() != null) {
            if (listBean.getPiclist().size() == 1) {
                imageInfos.add(parseImageInfo(listBean));
            } else {
                int size = listBean.getPiclist().size();
                for (int i = 0; i < size; i++) {
                    FriendCircleModel.ListBean.PiclistBean piclistBean = listBean.getPiclist().get(i);
                    ImageInfo imageInfo = new ImageInfo();
                    imageInfo.setLongPic(false);
                    imageInfo.setThumbnailUrl(piclistBean.getPicurl());
                    imageInfo.setBigImageUrl(piclistBean.getPicurl_photo());
                    imageInfos.add(imageInfo);
                }
            }
        }

        mInnerContainerAdapter = new InnerContainerAdapter(holder.itemView.getContext(), imageInfos);
        holder.mPhotoContents.setAdapter(mInnerContainerAdapter);

    }

    private ImageInfo parseImageInfo(FriendCircleModel.ListBean listBean) {

        ImageInfo imageInfo = new ImageInfo();

        if (!TextUtils.isEmpty(listBean.getVideo().getDynamiclive())) {
            imageInfo.setVideopath(listBean.getVideo().getDynamiclive());
            imageInfo.setVideo(true);
            imageInfo.setLongPic(false);
            imageInfo.setSinglePic(false);
            imageInfo.setLivelength(listBean.getVideo().getVideolength());
            imageInfo.setThumbnailUrl(listBean.getVideo().getDynamiclivepic());
            imageInfo.setImageViewHeight(10);
            imageInfo.setImageViewWidth(10);
        } else if (listBean.getPiclist().size() == 1) {
            FriendCircleModel.ListBean.PiclistBean piclistBean = listBean.getPiclist().get(0);
            int width = Integer.parseInt(piclistBean.getWidth());
            int height = Integer.parseInt(piclistBean.getHeight());
            if (width * 16 < height * 9) {
                imageInfo.setLongPic(true);
            }
            imageInfo.setVideo(false);
            imageInfo.setLivelength("");
            imageInfo.setVideopath("");
            imageInfo.setImageViewWidth(width);
            imageInfo.setImageViewHeight(height);
            imageInfo.setSinglePic(true);
            imageInfo.setThumbnailUrl(piclistBean.getPicurl());
            imageInfo.setBigImageUrl(piclistBean.getPicurl_photo());

        }

        return imageInfo;

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private PhotoContents mPhotoContents;

        public ViewHolder(View itemView) {
            super(itemView);
            mPhotoContents = itemView.findViewById(R.id.nine_photo_content);
        }
    }

    private class InnerContainerAdapter extends PhotoContentsBaseAdapter {


        private Context context;
        private List<ImageInfo> datas;

        InnerContainerAdapter(Context context, List<ImageInfo> datas) {
            this.context = context;
            this.datas = new ArrayList<>();
            this.datas.addAll(datas);
        }

        @Override
        public ImageView onCreateView(ImageView convertView, ViewGroup parent, int position, boolean videoTrue, boolean longPic, String videoTime) {
            if (convertView == null) {
                convertView = new ImageWrapper(context, videoTrue, longPic, videoTime);
                convertView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            return convertView;
        }

        @Override
        public void onBindData(int position, @NonNull ImageView convertView) {
            ImageLoader.load(datas.get(position).getThumbnailUrl(), convertView);
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public List<ImageInfo> getDatas() {
            return datas;
        }

        public void updateData(List<ImageInfo> datas) {
            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataChanged();
        }
    }
}
