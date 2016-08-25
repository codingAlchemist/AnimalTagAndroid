package jasondebottis.animaltagandroid.Views.CustomViews;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.databinding.PageIndicatorBinding;


public class PageIndicator extends FrameLayout {

    private PageIndicatorBinding mBinding;
    private Boolean mIsCurrent;

    public PageIndicator(Context context) {
        super(context);
        init(context);
    }

    public PageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context inContext) {
        LayoutInflater inflater = LayoutInflater.from(inContext);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.page_indicator, this, true);
    }

    public void setCurrent(boolean inCurrent) {
        mIsCurrent = inCurrent;
        setBackground(mIsCurrent ? getResources().getDrawable(R.drawable.page_indicator_selected, null) : getResources().getDrawable(R.drawable.page_indicator_normal, null));
    }

    public Boolean getIsCurrent() {
        return mIsCurrent;
    }
}
