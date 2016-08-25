package jasondebottis.animaltagandroid.Views.CustomViews;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import jasondebottis.animaltagandroid.MainActivity;
import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.databinding.GalleryViewPagerBinding;

public class GalleryViewPager extends RelativeLayout implements ViewPager.OnPageChangeListener {


    private GalleryViewPagerBinding mBinding;
    private List<GalleryImageFragment> mImageFragments = new ArrayList<>();

    private FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(MainActivity.GetFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return mImageFragments.get(0);
                case 1:
                    return mImageFragments.get(1);
                case 2:
                    return mImageFragments.get(2);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mImageFragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    };


    public GalleryViewPager(Context context) {
        super(context);
        init(context);
    }

    public GalleryViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context inContext) {
        LayoutInflater inflater = (LayoutInflater) inContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.gallery_view_pager, this, true);
        mBinding.galleryViewPager.addOnPageChangeListener(this);
    }

    public void SetImageUrls(List<String> inImageUrls) {
        for (String url : inImageUrls) {
            GalleryImageFragment imageFragment = GalleryImageFragment.newInstance(url);
            mImageFragments.add(imageFragment);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.pageIndicatorSize), (int) getResources().getDimension(R.dimen.pageIndicatorSize));
            params.weight = 1;
            params.setMarginStart(5);
            params.setMarginEnd(5);
            PageIndicator pageIndicator = new PageIndicator(getContext());
            pageIndicator.setLayoutParams(params);
            pageIndicator.setCurrent(false);
            mBinding.pageIndicatorLayout.addView(pageIndicator);

        }

        mBinding.galleryViewPager.setAdapter(mFragmentPagerAdapter);
        mBinding.galleryViewPager.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mBinding.pageIndicatorLayout.getChildCount(); i++) {
            PageIndicator indicator = (PageIndicator) mBinding.pageIndicatorLayout.getChildAt(i);
            if (i == position) {
                indicator.setCurrent(true);
            } else {
                indicator.setCurrent(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
