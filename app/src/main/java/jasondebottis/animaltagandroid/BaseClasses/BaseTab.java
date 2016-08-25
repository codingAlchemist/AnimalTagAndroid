package jasondebottis.animaltagandroid.BaseClasses;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.databinding.TabContainerBinding;


public class BaseTab extends RelativeLayout {
    protected TabContainerBinding mBinding;
    public final int kTabContainerHeight = 1400;
    public int mColor;
    public String mTitle;
    public Integer mTabHeight = this.getHeight() / 4;

    public boolean isCurrentlySelected;


    public BaseTab(Context context) {
        super(context);
        setWillNotDraw(false);
    }


    public BaseTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.tab_container, this, false);
        setWillNotDraw(false);


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TabStyle);
        try {
            mColor = ta.getInt(R.styleable.TabStyle_tabColor, 0);
            mTitle = ta.getString(R.styleable.TabStyle_tabTitle);
        } finally {
            ta.recycle();
        }
    }
}
