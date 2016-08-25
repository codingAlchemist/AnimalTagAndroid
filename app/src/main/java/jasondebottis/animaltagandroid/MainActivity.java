package jasondebottis.animaltagandroid;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import jasondebottis.animaltagandroid.Fragments.StartUpFragment;
import jasondebottis.animaltagandroid.Utilities.NavUtility;
import jasondebottis.animaltagandroid.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    public static Context mContext;

    private static FragmentManager mFragmentManager;
    private ActivityMainBinding mActivityMainBinding;
    private List<Fragment> mFragments;

    public static Context GetContext() {
        return mContext;
    }

    public static FragmentManager GetFragmentManager() {
        return mFragmentManager;
    }

    public static void NavigateTo(NavUtility.DestinationFragment inDestinationFragment, Bundle inArgs) {
        NavUtility.Navigate(inDestinationFragment, mFragmentManager, inArgs);
    }

    public static void NavigateTo(NavUtility.DestinationFragment inDestinationFragment) {
        NavUtility.Navigate(inDestinationFragment, mFragmentManager, null);
    }

    public static void GoBack() {
        NavUtility.GoBack(mFragmentManager);
    }

    public static void GoToStart() {
        NavUtility.GoToStart(mFragmentManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mContext = getApplicationContext();
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, StartUpFragment.newInstance())
            .commit();
    }


}
