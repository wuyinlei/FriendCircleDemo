package ruolan.com.friendcircledemo.nineimage;

/**
 * Created by 大灯泡 on 2016/11/9.
 */

public class PhotoAdapterObservable extends PhotoImageObservable<PhotoBaseDataObserver> {

    public void notifyChanged() {
        synchronized (mObservers) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onChanged();
            }
        }
    }

    public void notifyInvalidated() {
        synchronized (mObservers) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onInvalidated();
            }
        }
    }
}
