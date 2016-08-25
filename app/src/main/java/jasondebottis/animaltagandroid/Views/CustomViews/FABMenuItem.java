package jasondebottis.animaltagandroid.Views.CustomViews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.databinding.FabMenuItemBinding;


public class FABMenuItem extends RelativeLayout implements View.OnClickListener {

    private FabMenuItemBinding mBinding;
    private String mTag;
    private OnClickFabItemListener mOnClickFabItemListener;
    private Bitmap mFabIcon;

    public void setOnClickFabItemListener(OnClickFabItemListener inOnClickFabItemListener) {
        mOnClickFabItemListener = inOnClickFabItemListener;
    }

    public FABMenuItem(Context context) {
        super(context);
        init(context);
    }

    public FABMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        Resources resources = getResources();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FABMenuItem);
        try {

            mFabIcon = BitmapFactory.decodeResource(resources, typedArray.getResourceId(R.styleable.FABMenuItem_menuItemIcon, R.drawable.ic_action_list));
            mTag = typedArray.getString(R.styleable.FABMenuItem_menuItemTag);
            mBinding.fabMenuIcon.setImageBitmap(mFabIcon);
        } finally {
            typedArray.recycle();
        }

    }

    public void init(Context inContext) {
        LayoutInflater inflater = (LayoutInflater) inContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fab_menu_item, this, true);
        mBinding.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (mOnClickFabItemListener != null) {
            mOnClickFabItemListener.didClickItem(mTag);
        }
    }

    public interface OnClickFabItemListener {
        void didClickItem(String tag);
    }
}
