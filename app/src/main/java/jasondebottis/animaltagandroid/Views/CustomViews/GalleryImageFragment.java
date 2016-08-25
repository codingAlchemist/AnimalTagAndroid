package jasondebottis.animaltagandroid.Views.CustomViews;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.Utilities.ImageUtiliy;
import jasondebottis.animaltagandroid.databinding.GalleryImageFragmentBinding;


public class GalleryImageFragment extends Fragment {

    private GalleryImageFragmentBinding mBinding;
    private String mImageUrl;

    public static GalleryImageFragment newInstance(String inImageUrl) {
        GalleryImageFragment fragment = new GalleryImageFragment();
        fragment.mImageUrl = inImageUrl;

        return fragment;
    }

    public GalleryImageFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.gallery_image_fragment, container, false);
        ImageUtiliy.LoadImage(mImageUrl, mBinding.animalImageView);

        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
