package ruolan.com.friendcircledemo.nineimage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

import ruolan.com.friendcircledemo.R;
import ruolan.com.friendcircledemo.utils.DisplayUtil;

/**
 * Created by wuyinlei on 2018/1/4.
 *
 * @function  用于图片占位和按下响应处理
 */

public class ImageWrapper extends android.support.v7.widget.AppCompatImageView {

    //前景层
    private Drawable mForegroundDrawable;
    private Rect mCachedBounds = new Rect();


    private boolean mVideoTrue = false;  //是否是视频标志
    private boolean mLongPic = true;  //长图标志

    private int maskColor = 0x00000000;   //默认的遮盖颜色
    private float textSize = 13;          //显示文字的大小单位sp
    private int textColor = 0xFFFFFFFF;   //显示文字的颜色

    private TextPaint textPaint;              //文字的画笔
    private String mVideoTime = "";                  //要绘制的文字



    public ImageWrapper(Context context, boolean videoTrue, boolean longPic,String videoTime) {
        this(context, null);
        mVideoTrue = videoTrue;
        mLongPic = longPic;
        mVideoTime = videoTime;
    }

    public ImageWrapper(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setFocusable(false);

        initTouchResource(context,attrs,true);

        initTextPaint();
    }

    private void initTextPaint() {

        textPaint = new TextPaint();
        textPaint.setTextAlign(Paint.Align.CENTER);  //文字居中对齐
        textPaint.setAntiAlias(true);                //抗锯齿
        textPaint.setTextSize(textSize);             //设置文字大小
        textPaint.setColor(textColor);

    }

    /**
     *初始化按下的颜色资源
     */
    private void initTouchResource(Context context, AttributeSet attrs, boolean defaultGroundColor) {

        @SuppressLint("CustomViewStyleable") final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageViewWrapper);
        mForegroundDrawable = a.getDrawable(R.styleable.ImageViewWrapper_foregroundColor);
        if (mForegroundDrawable instanceof ColorDrawable || (attrs == null && defaultGroundColor)) {
            int foreGroundColor = a.getColor(R.styleable.ImageViewWrapper_foregroundColor, 0x88aa2b2b);
            mForegroundDrawable = new StateListDrawable();
            ColorDrawable forceDrawable = new ColorDrawable(foreGroundColor);
            ColorDrawable normalDrawable = new ColorDrawable(Color.TRANSPARENT);
            ((StateListDrawable) mForegroundDrawable).addState(new int[]{android.R.attr.state_pressed}, forceDrawable);
            ((StateListDrawable) mForegroundDrawable).addState(new int[]{android.R.attr.state_focused}, forceDrawable);
            ((StateListDrawable) mForegroundDrawable).addState(new int[]{android.R.attr.state_enabled}, normalDrawable);
            ((StateListDrawable) mForegroundDrawable).addState(new int[]{}, normalDrawable);
        }
        if (mForegroundDrawable != null) {
            mForegroundDrawable.setCallback(this);
        }
        a.recycle();
    }


    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (mForegroundDrawable != null && mForegroundDrawable.isStateful()) {
            mForegroundDrawable.setState(getDrawableState());
            invalidate();
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //这个是按下的前景色
        if (mForegroundDrawable != null) {
            if (getDrawable() != null) {
                mForegroundDrawable.setBounds(getDrawable().getBounds());
            } else {
                mForegroundDrawable.setBounds(mCachedBounds);
            }
            mForegroundDrawable.draw(canvas);
        }

        //  视频的时候的视频标志
        if (mVideoTrue) {

            //绘制视频时长
            if (!TextUtils.isEmpty(mVideoTime)) {

                Paint pFont = new Paint();
                Rect rect = new Rect();
                pFont.getTextBounds(mVideoTime, 0, mVideoTime.length(), rect);

                float baseY = getHeight() - rect.height() - DisplayUtil.dip2px(3);
                float baseX = getWidth() - rect.width() + (textPaint.ascent() + textPaint.descent())/2  - DisplayUtil.dip2px(10);

                canvas.drawText(mVideoTime, baseX, baseY, textPaint);
            }

            Bitmap bm = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.details_live_go);//图片
            // 将画布坐标系移动到画布中央
            canvas.translate((getWidth() - bm.getWidth()) / 2, (getHeight() - bm.getHeight()) / 2);
            // 指定图片绘制区域(全部的bitmap需要画)
            Rect src = new Rect(0, 0, bm.getWidth(), bm.getHeight());
            // 指定图片在屏幕上显示的区域
            Rect dst = new Rect(0, 0, DisplayUtil.dip2px(34), DisplayUtil.dip2px(34));
            canvas.drawBitmap(bm, src, dst, textPaint);
        } else if (mLongPic) {
            Bitmap bm = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.pic_long);//图片
            canvas.translate(getWidth() - bm.getWidth() - DisplayUtil.dip2px(3), getHeight() - bm.getHeight() -  DisplayUtil.dip2px(3));
            // 指定图片绘制区域(全部的bitmap需要画)
            Rect src = new Rect(0, 0, bm.getWidth(), bm.getHeight());
            // 指定图片在屏幕上显示的区域
            Rect dst = new Rect(0, 0, DisplayUtil.dip2px(29), DisplayUtil.dip2px(15));
            canvas.drawBitmap(bm, src, dst, textPaint);
        }
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mForegroundDrawable != null) mCachedBounds.set(0, 0, w, h);
    }


}
