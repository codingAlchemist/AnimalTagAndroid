package jasondebottis.animaltagandroid.Fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.veinhorn.scrollgalleryview.MediaInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jasondebottis.animaltagandroid.BuildConfig;
import jasondebottis.animaltagandroid.MainActivity;
import jasondebottis.animaltagandroid.Models.AnimalModel;
import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.Services.DBService;
import jasondebottis.animaltagandroid.Utilities.AlertUtility;
import jasondebottis.animaltagandroid.Utilities.ImageUtiliy;
import jasondebottis.animaltagandroid.Utilities.PicassoImageLoader;
import jasondebottis.animaltagandroid.databinding.AnimalDetailFragmentBinding;

import static android.content.ContentValues.TAG;


public class AnimalDetailFragment extends Fragment {
    public static final String kAnimal = "Animal";
    private AnimalDetailFragmentBinding mBinding;
    private AnimalModel mAnimalModel;
    private List<String> mImageUrls = new ArrayList<>();

    public static AnimalDetailFragment newInstance() {
        AnimalDetailFragment fragment = null;
        if (fragment == null) {
            fragment = new AnimalDetailFragment();
        }
        return fragment;
    }

    public AnimalDetailFragment() {

    }

    public final View.OnClickListener kGoBack = new View.OnClickListener() {
        @Override
        public void onClick(View inView) {
            MainActivity.GoBack();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.animal_detail_fragment, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolBar);
        mBinding.toolBar.setTitle("Animal Detail");
        mBinding.toolBar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mBinding.toolBar.setNavigationIcon(R.drawable.ic_keyboard_backspace);
        mBinding.toolBar.setNavigationOnClickListener(kGoBack);
        return mBinding.getRoot();
    }


    private void setAnimal(AnimalModel animal) {
        mBinding.setAnimal(animal);

        String image1 = BuildConfig.baseUrl + "/app/public/" + animal.mImage1;
        String image2 = BuildConfig.baseUrl + "/app/public/" + animal.mImage2;
        String image3 = BuildConfig.baseUrl + "/app/public/" + animal.mImage3;

        mImageUrls.add(image1);
        mImageUrls.add(image2);
        mImageUrls.add(image3);
        ImageUtiliy.LoadImage(image1, mBinding.animalImageView);

        List<MediaInfo> info = new ArrayList<>();
        for (String url : mImageUrls) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            info.add(MediaInfo.mediaLoader(new PicassoImageLoader(url)));
        }

        mBinding.animalImageGallery
            .setThumbnailSize(100)
            .setZoom(true)
            .setFragmentManager(MainActivity.GetFragmentManager())
            .addMedia(info);
        mBinding.invalidateAll();
        mBinding.addToFavoritesButton.setOnClickListener(kOnClickSaveFavorite);
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle args = getArguments();
        mAnimalModel = args.getParcelable(kAnimal);
        setAnimal(mAnimalModel);
    }

    private final View.OnClickListener kOnClickSaveFavorite = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + mAnimalModel.mCommon);
            List<AnimalModel> savedAnimals = DBService.GetAllSavedAnimals();
            AnimalModel animal = new AnimalModel(
                mAnimalModel.mCommon,
                mAnimalModel.mLatin,
                mAnimalModel.mType,
                mAnimalModel.mShortDesc,
                mAnimalModel.mLongDesc,
                mAnimalModel.mImage1,
                mAnimalModel.mImage2,
                mAnimalModel.mImage3,
                mAnimalModel.mHabitat,
                mAnimalModel.mTag);
            for (AnimalModel animalModel : savedAnimals) {
                if (animalModel.mTag.contentEquals(mAnimalModel.mTag)) {
                    String alertMessage = String.format(Locale.ENGLISH, "%s is already in your favorites", mAnimalModel.mCommon);
                    AlertUtility alertUtility = AlertUtility.NewInstance(alertMessage);
                    alertUtility.show(MainActivity.GetFragmentManager(), AlertUtility.DIALOG_TAG);
                } else {
                    String alertMessage = String.format(Locale.ENGLISH, "%s Saved to favorites", mAnimalModel.mCommon);
                    AlertUtility alertUtility = AlertUtility.NewInstance(alertMessage);
                    alertUtility.show(MainActivity.GetFragmentManager(), AlertUtility.DIALOG_TAG);
                    animal.save();
                }
            }
        }
    };
}
