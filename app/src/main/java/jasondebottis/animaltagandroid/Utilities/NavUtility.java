package jasondebottis.animaltagandroid.Utilities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import jasondebottis.animaltagandroid.Fragments.AnimalDetailFragment;
import jasondebottis.animaltagandroid.Fragments.LocationIdFragment;
import jasondebottis.animaltagandroid.Fragments.SavedAnimalsFragment;
import jasondebottis.animaltagandroid.Fragments.StartUpFragment;
import jasondebottis.animaltagandroid.Fragments.TabFragment;
import jasondebottis.animaltagandroid.Fragments.TakeAnimalPictureFragment;
import jasondebottis.animaltagandroid.R;


public class NavUtility {
    private static Fragment mFragment;

    public enum DestinationFragment {
        kTabFragment,
        kStartUpFragment,
        kAnimalDetailFragment,
        kFavoriteAnimals,
        kLocationIdFragment,
        kTakeAnimalPictureFragment
    }

    public static void Navigate(DestinationFragment inFragment, FragmentManager inFragmentManager, Bundle inArgs) {
        mFragment = null;
        switch (inFragment) {
            case kStartUpFragment:
                mFragment = new StartUpFragment();
                break;
            case kTabFragment:
                mFragment = new TabFragment();
                break;
            case kAnimalDetailFragment:
                mFragment = new AnimalDetailFragment();
                break;
            case kFavoriteAnimals:
                mFragment = new SavedAnimalsFragment();
                break;
            case kLocationIdFragment:
                mFragment = new LocationIdFragment();
                break;
            case kTakeAnimalPictureFragment:
                mFragment = new TakeAnimalPictureFragment();
                break;
            default:
                break;
        }

        if (inArgs != null) {
            mFragment.setArguments(inArgs);
        }
        inFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left)
            .replace(R.id.containerFragment, mFragment)
            .addToBackStack("fragment")
            .commit();
    }


    public static void GoBack(FragmentManager inFragmentManager) {
        inFragmentManager.popBackStack();
    }

    public static void GoToStart(FragmentManager inFragmentManager) {
        inFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right)
            .replace(R.id.containerFragment, StartUpFragment.newInstance())
            .commit();
    }
}
