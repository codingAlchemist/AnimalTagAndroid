package jasondebottis.animaltagandroid.Fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jasondebottis.animaltagandroid.Adapters.AnimalAdapter;
import jasondebottis.animaltagandroid.BaseClasses.BaseTab;
import jasondebottis.animaltagandroid.MainActivity;
import jasondebottis.animaltagandroid.Models.AnimalModel;
import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.Services.RetrofitService;
import jasondebottis.animaltagandroid.Utilities.AnimationUtility;
import jasondebottis.animaltagandroid.databinding.TabFragmentBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TabFragment extends Fragment implements View.OnClickListener {

    private List<AnimalModel> mAnimals = new ArrayList<>();
    public static final String TAG = TabFragment.class.getSimpleName();
    private TabFragmentBinding mBinding;

    private BaseTab mBirdTab;
    private BaseTab mRepilteTab;
    private BaseTab mInsectTab;


    public TabFragment() {
    }


    private final Callback<List<AnimalModel>> kGetAnimalsCallback = new Callback<List<AnimalModel>>() {
        @Override
        public void onResponse(Call<List<AnimalModel>> inCall, Response<List<AnimalModel>> inResponse) {
            if (inResponse.isSuccessful()) {
                mAnimals = inResponse.body();
                populateAnimals(mAnimals);
            }
        }


        @Override
        public void onFailure(Call<List<AnimalModel>> inCall, Throwable inThrowable) {
            Log.d(TAG, "onFailure: " + inThrowable.getMessage());
        }
    };


    private void populateAnimals(List<AnimalModel> inAnimals) {

        List<AnimalModel> birds = new ArrayList<>();
        List<AnimalModel> reptiles = new ArrayList<>();
        List<AnimalModel> insects = new ArrayList<>();

        for (AnimalModel animal : inAnimals) {

            if (animal.mType.contentEquals("Bird")) {
                AnimalModel dataAnimal = animal;
                birds.add(dataAnimal);

            } else if (animal.mType.contentEquals("Reptile")) {
                AnimalModel dataAnimal = animal;
                reptiles.add(dataAnimal);

            } else if (animal.mType.contentEquals("Insect")) {
                AnimalModel dataAnimal = animal;
                insects.add(dataAnimal);
            }
        }

        mBinding.birdTabRecyclerView.GetAdapter().setDataItems(birds);
        mBinding.reptileTabRecyclerView.GetAdapter().setDataItems(reptiles);
        mBinding.insectTabRecyclerView.GetAdapter().setDataItems(insects);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.tab_fragment, container, false);

        mBirdTab = mBinding.birdTab;
        mRepilteTab = mBinding.reptileTab;
        mInsectTab = mBinding.insectTab;

        mBinding.regionOne.setOnClickListener(this);
        mBinding.regionTwo.setOnClickListener(this);
        mBinding.regionThree.setOnClickListener(this);

        //orriginal start Z of tabs
        mBirdTab.setZ(2);
        mInsectTab.setZ(2);
        mRepilteTab.setZ(2);

        //these never change
        mBinding.regionOne.setZ(4);
        mBinding.regionTwo.setZ(4);
        mBinding.regionThree.setZ(4);

        mBinding.putAwayAllRegion.setZ(4);
        mBinding.putAwayAllRegion.setOnClickListener(this);

        mBinding.cardFolderImageView.setZ(1);
        mBinding.cardFolderCoverImageView.setZ(3);


        mBinding.toolBar.setTitle(R.string.animalList);
        mBinding.toolBar.setNavigationIcon(R.drawable.ic_keyboard_backspace);
        mBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.GoToStart();
            }
        });

        mBinding.birdTabRecyclerView.setAdapter(new AnimalAdapter());
        mBinding.reptileTabRecyclerView.setAdapter(new AnimalAdapter());
        mBinding.insectTabRecyclerView.setAdapter(new AnimalAdapter());

        RetrofitService.GetAnimals(kGetAnimalsCallback);
        return mBinding.getRoot();
    }


    public BaseTab GetSelectedTab() {
        BaseTab baseTab = null;
        if (mBirdTab.isCurrentlySelected) {
            baseTab = mBirdTab;
        } else if (mRepilteTab.isCurrentlySelected) {
            baseTab = mRepilteTab;
        } else if (mInsectTab.isCurrentlySelected) {
            baseTab = mInsectTab;
        }
        return baseTab;
    }


    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.regionOne) {
            if (!mBirdTab.isCurrentlySelected) {
                AnimationUtility.ToggleTabs(GetSelectedTab(), mBirdTab);
            }
        } else if (v.getId() == R.id.regionTwo) {
            if (!mRepilteTab.isCurrentlySelected) {
                AnimationUtility.ToggleTabs(GetSelectedTab(), mRepilteTab);
            }

        } else if (v.getId() == R.id.regionThree) {
            if (!mInsectTab.isCurrentlySelected) {
                AnimationUtility.ToggleTabs(GetSelectedTab(), mInsectTab);

            }
        } else if (v.getId() == R.id.putAwayAllRegion) {
            AnimationUtility.ToggleTabs(GetSelectedTab(), null);
        }

    }
}
